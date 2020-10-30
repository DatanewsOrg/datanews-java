package io.datanews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  public static void setGlobalApiKey(@NonNull String apiKey) {
    globalApiKey = apiKey;
  }

  public static String getGlobalApiKey() {
    return globalApiKey;
  }

  public static SearchClient search() {
    return search(globalApiKey);
  }

  public static SearchClient search(@NonNull String apiKey) {
    return new SearchClient(apiKey, REQUESTOR, JSON_HANDLER);
  }

  public static MonitorsClient monitors() {
    return monitors(globalApiKey);
  }

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
