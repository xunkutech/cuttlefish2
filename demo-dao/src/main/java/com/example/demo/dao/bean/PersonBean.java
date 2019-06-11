package com.example.demo.dao.bean;

import com.example.demo.model.enums.Gender;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class PersonBean {

  protected String id;

  protected String name;
  protected Gender gender;
  protected BinaryObjectBean portraitBean = new BinaryObjectBean();

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
