package io.datanews.common;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import io.datanews.exceptions.DatanewsIOException;
import io.datanews.exceptions.InvalidRequestException;
import io.datanews.exceptions.AuthorizationException;
import io.datanews.exceptions.RateLimitException;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpRequestor implements Requestor {
  private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

  private final OkHttpClient client;

  public OkHttpRequestor(OkHttpClient client) {
    this.client = client;
  }

  @Override
  public <T> T get(
      String address,
      Map<String, String> headers,
      Map<String, Set<String>> params,
      Function<? super Reader, T> handler
  ) throws DatanewsIOException {
    HttpUrl.Builder urlBuilder = HttpUrl.get(address).newBuilder();

    for (String name : params.keySet()) {
      for (String value : params.get(name)) {
        urlBuilder.addQueryParameter(name, value);
      }
    }

    Request request = new Request.Builder()
        .headers(Headers.of(headers))
        .url(urlBuilder.build())
        .build();

    return handleRequest(request, handler);
  }

  @Override
  public <T> T post(
      String url,
      Map<String, String> headers,
      String data,
      Function<? super Reader, T> handler
  ) throws DatanewsIOException {
    Request request = new Request.Builder()
        .url(HttpUrl.get(url))
        .headers(Headers.of(headers))
        .post(RequestBody.create(data, JSON))
        .build();

    return handleRequest(request, handler);
  }

  @Override
  public void delete(
      String url,
      Map<String, String> headers
  ) throws DatanewsIOException {
    Request request = new Request.Builder()
        .url(HttpUrl.get(url))
        .headers(Headers.of(headers))
        .delete()
        .build();

    handleRequest(request, reader -> null);
  }

  private <T> T handleRequest(Request request, Function<? super Reader, T> handler) throws DatanewsIOException {
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        switch (response.code()) {
          case 400:
          case 404:
            throw new InvalidRequestException(response.message());
          case 401:
          case 403:
            throw new AuthorizationException(response.message());
          case 429:
            throw new RateLimitException(response.message());
          case 500:
          case 502:
          case 503:
          case 504:
            throw new DatanewsIOException("Internal server error: " + response.message());
          default:
            throw new DatanewsIOException("Unknown error: " + response.message());
        }
      }

      ResponseBody body = Objects.requireNonNull(response.body(), "Response body can't be null");
      return handler.apply(body.charStream());
    } catch (IOException e) {
      throw new DatanewsIOException("Can't execute request", e);
    }
  }
}
