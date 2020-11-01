package io.datanews.search;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import io.datanews.common.JsonHandler;
import io.datanews.common.Requestor;
import io.datanews.exceptions.DatanewsIOException;
import lombok.NonNull;

/**
 * This class queries Datanews search API.
 * <p>
 * Each of the methods may throw an instance of a subclass of
 * {@link DatanewsIOException} if request was unsuccessful. In particular:
 * <ul>
 *   <li>
 *     {@link io.datanews.exceptions.AuthorizationException} - authorization failed (e.g. invalid API key).
 *     Corresponds to status codes 401 or 403.
 *   </li>
 *   <li>
 *     {@link io.datanews.exceptions.InvalidRequestException} - request is invalid in some way.
 *     Corresponds to status codes 400 or 404.
 *   </li>
 *   <li>
 *     {@link io.datanews.exceptions.RateLimitException} - rate limit has been exceeded. Corresponds to 429 status code.
 *   </li>
 *   <li>
 *     {@link DatanewsIOException} - unknown errors and internal server errors (codes 50*).
 *   </li>
 * </ul>
 * <p>
 * See <a href="https://datanews.io/docs">official docs</a> for more info.
 */
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

  /**
   * Queries the {@code /headlines} endpoint.
   * <p>
   * For more info, check out <a href="https://datanews.io/docs/headlines">Headlines API docs</a>.
   * @param params - parameters to send to the server
   * @return response
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code params} is null
   */
  public NewsResponse headlines(@NonNull HeadlinesParams params) throws DatanewsIOException {
    return makeGetRequest("headlines", params.getRawParams(), NewsResponse.class);
  }

  /**
   * Queries the {@code /news} endpoint.
   * <p>
   * For more info, check out <a href="https://datanews.io/docs/news">News API docs</a>.
   * @param params - parameters to send to the server
   * @return response
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code params} is null
   */
  public NewsResponse news(@NonNull NewsParams params) throws DatanewsIOException {
    return makeGetRequest("news", params.getRawParams(), NewsResponse.class);
  }

  /**
   * Queries the {@code /sources} endpoint.
   * <p>
   * For more info, check out <a href="https://datanews.io/docs/sources">Sources API docs</a>.
   * @param params - parameters to send to the server
   * @return response
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code params} is null
   */
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
