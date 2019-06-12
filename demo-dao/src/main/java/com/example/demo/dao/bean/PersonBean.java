package com.example.demo.dao.bean;

import com.example.demo.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
public abstract class PersonBean {

  protected Long id;

  protected String name;
  protected Gender gender;
  protected BinaryObjectBean portraitBean = new BinaryObjectBean();
  protected Long creationDate;

//  public enum Gender {
//    MALE,
//    FEMALE;
//
//    @Override
//    public String toString() {
//      return name().toLowerCase();
//    }
//  }
}
