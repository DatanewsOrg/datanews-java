package io.datanews.exceptions;

public class RateLimitException extends DatanewsIOException {
  public RateLimitException(String message) {
    super(message);
  }
}
