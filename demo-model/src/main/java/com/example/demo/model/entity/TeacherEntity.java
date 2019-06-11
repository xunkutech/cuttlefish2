package com.example.demo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
    name = "demo_teacher",
    indexes = {@Index(name = "idx_name", columnList = "name")})
public class TeacherEntity extends PersonEntity implements Serializable {

  private static final long serialVersionUID = -2176765934182526700L;

  @Transient private List<CourseEntity> courseEntities;
}
