package io.datanews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.Nullable;

import io.datanews.common.GsonHandler;
import io.datanews.common.JsonHandler;
import io.datanews.common.OkHttpRequestor;
import io.datanews.common.Requestor;
import io.datanews.monitoring.MonitorsClient;
import io.datanews.search.SearchClient;
import lombok.NonNull;
import okhttp3.OkHttpClient;

public final class Datanews {
  private static volatile String globalApiKey;
  private static final JsonHandler JSON_HANDLER = computeJsonHandler();
  private static final Requestor REQUESTOR = new OkHttpRequestor(new OkHttpClient());

  private Datanews() {
  }

  /**
   * Sets global API key. This will later be used in {@link #monitors()} and {@link #search()}.
   * @param apiKey - value of API key.
   * @throws NullPointerException if {@code apiKey} is {@code null}.
   */
  public static void setGlobalApiKey(@NonNull String apiKey) {
    globalApiKey = apiKey;
  }

  /**
   * Getter for the global API key.
   * @return a value for the global API key. This may be {@code null} if API has not yet been set.
   */
  @Nullable
  public static String getGlobalApiKey() {
    return globalApiKey;
  }

  /**
   * Creates a search API client using global API key.
   * @return an instance on {@link SearchClient}.
   * @throws IllegalStateException if global API key hasn't been set via {@link #setGlobalApiKey(String)}
   */
  public static SearchClient search() {
    if (globalApiKey == null) {
      throw new IllegalStateException("Global API key has not been set");
    }

    return search(globalApiKey);
  }

  /**
   * Creates a search API client using the provided API key.
   * @param apiKey - value of API key.
   * @return an instance on {@link SearchClient}.
   * @throws NullPointerException if {@code apiKey} is null
   */
  public static SearchClient search(@NonNull String apiKey) {
    return new SearchClient(apiKey, REQUESTOR, JSON_HANDLER);
  }

  /**
   * Creates a monitoring API client using global API key.
   * @return an instance on {@link MonitorsClient}.
   * @throws IllegalStateException if global API key hasn't been set via {@link #setGlobalApiKey(String)}
   */
  public static MonitorsClient monitors() {
    if (globalApiKey == null) {
      throw new IllegalStateException("Global API key has not been set");
    }

    return monitors(globalApiKey);
  }

  /**
   * Creates a search API client using the provided API key.
   * @param apiKey - value of API key.
   * @return an instance on {@link SearchClient}.
   * @throws NullPointerException if {@code apiKey} is null
   */
  public static MonitorsClient monitors(@NonNull String apiKey) {
    return new MonitorsClient(apiKey, REQUESTOR, JSON_HANDLER);
  }

  private static JsonHandler computeJsonHandler() {
    Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create();

    return new GsonHandler(gson);
  }
}
