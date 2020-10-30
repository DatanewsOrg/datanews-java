package io.datanews.common;

import com.google.gson.Gson;

import java.io.Reader;

public class GsonHandler implements JsonHandler {
  private final Gson gson;

  public GsonHandler(Gson gson) {
    this.gson = gson;
  }

  @Override
  public <T> T decode(Reader reader, Class<T> clazz) {
    return gson.fromJson(reader, clazz);
  }

  @Override
  public String encode(Object data) {
    return gson.toJson(data);
  }
}
