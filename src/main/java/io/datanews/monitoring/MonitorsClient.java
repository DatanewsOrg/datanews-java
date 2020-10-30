package io.datanews.monitoring;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.datanews.common.JsonHandler;
import io.datanews.common.Requestor;
import io.datanews.exceptions.DatanewsIOException;
import io.datanews.model.Monitor;
import lombok.NonNull;

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

  public List<Monitor> list() throws DatanewsIOException {
    Function<? super Reader, List<Monitor>> decoder = jsonHandler
        .getDecoder(Monitor[].class)
        .andThen(Arrays::asList)
        .andThen(ArrayList::new);

    return makeGetRequest("list", decoder);
  }

  public Monitor list(@NonNull String monitorId) throws DatanewsIOException {
    return makeGetRequest("list/" + monitorId, jsonHandler.getDecoder(Monitor.class));
  }

  public Monitor create(@NonNull CreateMonitorParams params) throws DatanewsIOException {
    String body = jsonHandler.encode(params);
    String url = String.format(BASE_URL, "create");
    return requestor.post(url, createHeaders(), body, jsonHandler.getDecoder(Monitor.class));
  }

  public void delete(@NonNull String monitorId) throws DatanewsIOException {
    String url = String.format(BASE_URL, "delete/" + monitorId);
    requestor.delete(url, createHeaders());
  }

  public MonitorsLatestResponse latest(@NonNull String runId) throws DatanewsIOException {
    return makeGetRequest("latest/" + runId, jsonHandler.getDecoder(MonitorsLatestResponse.class));
  }

  private Map<String, String> createHeaders() {
    Map<String, String> headers = new HashMap<>();
    headers.put(API_KEY_HEADER_NAME, apiKey);
    return headers;
  }

  private <T> T makeGetRequest(String endpoint, Function<? super Reader, T> handler) throws DatanewsIOException {
    String url = String.format(BASE_URL, endpoint);
    return requestor.get(url, createHeaders(), handler);
  }
}
