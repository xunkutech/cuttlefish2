package com.example.demo.dao.exception;

import org.springframework.dao.DataAccessException;

public class DataInvalidException extends DataAccessException {

  public DataInvalidException(String msg) {
    super(msg);
  }

  public DataInvalidException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
