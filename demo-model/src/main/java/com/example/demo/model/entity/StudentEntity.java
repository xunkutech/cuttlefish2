package com.example.demo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
    name = "demo_student",
    indexes = {@Index(name = "idx_name", columnList = "name")})
public class StudentEntity extends PersonEntity implements Serializable {

  private static final long serialVersionUID = 4283959259264441441L;
}
