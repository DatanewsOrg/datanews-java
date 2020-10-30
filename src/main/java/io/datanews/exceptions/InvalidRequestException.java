package io.datanews.exceptions;

public class InvalidRequestException extends DatanewsIOException {
  public InvalidRequestException(String message) {
    super(message);
  }
}
