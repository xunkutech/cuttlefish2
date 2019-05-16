package com.example.demo.model;

import com.example.demo.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class PersonEntity extends BaseEntity {

  @Column(
      name = "name",
      nullable = false,
      length = 30)
  private String name;

  @Column(name = "gender", nullable = false)
  private Gender gender;
}
