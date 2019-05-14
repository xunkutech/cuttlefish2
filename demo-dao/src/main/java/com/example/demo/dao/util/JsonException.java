package com.example.demo.dao.util;

public class JsonException extends Exception {

  private static final long serialVersionUID = 7830510019204912580L;

  public JsonException() {}

  public JsonException(String message) {
    super(message);
  }

  public JsonException(String message, Throwable cause) {
    super(message, cause);
  }

  public JsonException(Throwable cause) {
    super(cause);
  }

  public JsonException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
