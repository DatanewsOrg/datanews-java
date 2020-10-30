package io.datanews.exceptions;

import java.io.IOException;

public class DatanewsIOException extends IOException {
  public DatanewsIOException(String message, Throwable cause) {
    super(message, cause);
  }

  public DatanewsIOException(String message) {
    super(message);
  }
}
