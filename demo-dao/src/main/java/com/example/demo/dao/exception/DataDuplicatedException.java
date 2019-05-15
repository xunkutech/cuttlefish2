package com.example.demo.dao.exception;

import org.springframework.dao.DataAccessException;

public class DataDuplicatedException extends DataAccessException {
  public DataDuplicatedException(String msg) {
    super(msg);
  }

  public DataDuplicatedException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
