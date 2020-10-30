package io.datanews.common;

import java.io.Reader;
import java.util.function.Function;

public interface JsonHandler {
  <T> T decode(Reader reader, Class<T> clazz);
  String encode(Object data);

  default <T> Function<? super Reader, T> getDecoder(Class<T> clazz) {
    return reader -> decode(reader, clazz);
  }
}
