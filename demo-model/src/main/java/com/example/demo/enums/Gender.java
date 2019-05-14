package com.example.demo.enums;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeConverter;

public enum Gender implements PersistableEnum<Integer> {
  MALE(0),
  FEMALE(1),
  ;

  @Getter @Setter private Integer enumKey;

  Gender(Integer key) {
    this.enumKey = key;
  }

  @javax.persistence.Converter(autoApply = true)
  public static class Converter extends PersistableEnum.Converter<Gender, Integer>
      implements AttributeConverter<Gender, Integer> {}
}
