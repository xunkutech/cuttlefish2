package com.example.demo.dao.exception;

/** Created by jf on 2019/4/24. */
public enum ErrorCode {
  INVALID_MOBILE_NUM(1000, "mobile.not.valid");

  private final int code;
  private final String message;

  private ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public static String getMessageByCode(int code) {
    for (ErrorCode errorCode : ErrorCode.values()) {
      if (errorCode.getCode() == code) {
        return errorCode.getMessage();
      }
    }
    return null;
  }
}
