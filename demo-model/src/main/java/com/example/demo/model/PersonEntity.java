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
      length = 30,
      columnDefinition = "VARCHAR(30) COLLATE 'utf8_mb4'")
  private String name;

  @Column(name = "gender", nullable = false, columnDefinition = "TINYINT(1)")
  private Gender gender;
}
