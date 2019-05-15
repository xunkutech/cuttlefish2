package com.example.demo.dao.bean;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class PersonBean {

  protected String id;

  protected String name;
  protected Gender gender;

  public enum Gender {
    MALE,
    FEMALE;

    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }
}
