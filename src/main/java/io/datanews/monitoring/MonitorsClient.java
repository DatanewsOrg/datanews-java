package io.datanews.monitoring;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.datanews.common.JsonHandler;
import io.datanews.common.Requestor;
import io.datanews.exceptions.DatanewsIOException;
import io.datanews.model.Monitor;
import lombok.NonNull;

/**
 * This class queries Datanews monitoring API.
 * <p>
 * Each of the methods may throw an instance of a subclass of {@link DatanewsIOException} if request was unsuccessful.
 * In particular:
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
 * For more info, check out <a href="https://datanews.io/docs/monitoring-overview">Monitoring API overview</a>.
 */
public class MonitorsClient {
  private static final String BASE_URL = "http://api.datanews.io/v1/monitors/%s";
  private static final String API_KEY_HEADER_NAME = "x-api-key";

  private final String apiKey;
  private final Requestor requestor;
  private final JsonHandler jsonHandler;

  public MonitorsClient(
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
   * Queries {@code monitors/list} endpoint.
   * @return all available monitors for this customer
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   */
  public List<Monitor> list() throws DatanewsIOException {
    Function<? super Reader, List<Monitor>> decoder = jsonHandler
        .getDecoder(Monitor[].class)
        .andThen(Arrays::asList)
        .andThen(ArrayList::new);

    return makeGetRequest("list", decoder);
  }

  /**
   * Queries {@code monitors/list/{monitorId}} endpoint.
   * @param monitorId - id of the monitor
   * @return the monitor with id {@code monitorId}
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code monitorId} is null.
   */
  public Monitor list(@NonNull String monitorId) throws DatanewsIOException {
    return makeGetRequest("list/" + monitorId, jsonHandler.getDecoder(Monitor.class));
  }

  /**
   * Queries {@code monitors/create} endpoint.
   * @param params - parameters to send to the server.
   * @return a newly created monitor.
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code params} is null.
   */
  public Monitor create(@NonNull CreateMonitorParams params) throws DatanewsIOException {
    String body = jsonHandler.encode(params);
    String url = String.format(BASE_URL, "create");
    return requestor.post(url, createHeaders(), body, jsonHandler.getDecoder(Monitor.class));
  }

  /**
   * Queries {@code monitors/delete} endpoint.
   * @param monitorId - id of the monitor to delete.
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code monitorId} is null.
   */
  public void delete(@NonNull String monitorId) throws DatanewsIOException {
    String url = String.format(BASE_URL, "delete/" + monitorId);
    requestor.delete(url, createHeaders());
  }

  /**
   * Queries {@code monitors/latest} endpoint.
   * @param runId - id of the run to retrieve.
   * @return information about the run with {@code runId}
   * @throws DatanewsIOException if response was unsuccessful (network error, invalid API key etc)
   * @throws NullPointerException if {@code runId} is null.
   */
  public MonitorsLatestResponse latest(@NonNull String runId) throws DatanewsIOException {
    return makeGetRequest("latest/" + runId, jsonHandler.getDecoder(MonitorsLatestResponse.class));
  }

  private Map<String, String> createHeaders() {
    return Collections.singletonMap(API_KEY_HEADER_NAME, apiKey);
  }

  private <T> T makeGetRequest(String endpoint, Function<? super Reader, T> handler) throws DatanewsIOException {
    String url = String.format(BASE_URL, endpoint);
    return requestor.get(url, createHeaders(), handler);
  }
}
