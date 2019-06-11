package com.example.demo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(
    name = "demo_student_course",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uniq_student_id_course_id",
          columnNames = {"student_id", "course_id"})
    })
public class StudentCourseEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 5157851741038838249L;

  @Column(
      name = "student_id",
      nullable = false,
      length = 64)
  private String studentEntityId;

  @Column(
      name = "course_id",
      nullable = false,
      length = 64)
  private String courseEntityId;
}
