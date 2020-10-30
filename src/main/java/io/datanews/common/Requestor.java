package io.datanews.common;

import java.io.Reader;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import io.datanews.exceptions.DatanewsIOException;

public interface Requestor {
  <T> T get(String url, Map<String, String> headers, Map<String, Set<String>> params, Function<? super Reader, T> handler)
      throws DatanewsIOException;

  default <T> T get(
      String url, Map<String, String> headers, Function<? super Reader, T> handler) throws DatanewsIOException {
    return get(url, headers, Collections.emptyMap(), handler);
  }

  <T> T post(String url, Map<String, String> headers, String data, Function<? super Reader, T> handler)
      throws DatanewsIOException;

  void delete(String url, Map<String, String> headers) throws DatanewsIOException;
}
