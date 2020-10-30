package io.datanews.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.datanews.common.JsonHandler;
import io.datanews.common.Requestor;
import io.datanews.exceptions.DatanewsIOException;
import lombok.NonNull;

public class SearchClient {
  static final String BASE_URL = "http://api.datanews.io/v1/%s";
  static final String API_KEY_HEADER_NAME = "x-api-key";

  private final String apiKey;
  private final Requestor requestor;
  private final JsonHandler jsonHandler;

  public SearchClient(
      @NonNull String apiKey,
      @NonNull Requestor requestor,
      @NonNull JsonHandler jsonHandler
  ) {
    this.apiKey = apiKey;
    this.requestor = requestor;
    this.jsonHandler = jsonHandler;
  }

  public String getApiKey() {
    return apiKey;
  }

  public NewsResponse headlines(@NonNull HeadlinesParams params) throws DatanewsIOException {
    return makeGetRequest("headlines", params.getRawParams(), NewsResponse.class);
  }

  public NewsResponse news(@NonNull NewsParams params) throws DatanewsIOException {
    return makeGetRequest("news", params.getRawParams(), NewsResponse.class);
  }

  public SourcesResponse sources(@NonNull SourcesParams params) throws DatanewsIOException {
    return makeGetRequest("sources", params.getRawParams(), SourcesResponse.class);
  }

  private <T> T makeGetRequest(String endpoint, Map<String, Set<String>> params, Class<T> clazz)
      throws DatanewsIOException {
    String url = String.format(BASE_URL, endpoint);
    Map<String, String> headers = Collections.singletonMap(API_KEY_HEADER_NAME, apiKey);
    return requestor.get(url, headers, params, jsonHandler.getDecoder(clazz));
  }
}
