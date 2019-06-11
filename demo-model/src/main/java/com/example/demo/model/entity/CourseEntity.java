package com.example.demo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "demo_course")
public class CourseEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = -4470404744576983785L;

  @Column(
      name = "course_name",
      nullable = false,
      length = 510,
      unique = true)
  private String courseName;

  @Column(
      name = "teacher_id",
      nullable = false,
      length = 64)
  private String teacherEntityId;

  @Transient private TeacherEntity teacherEntity;

  @Transient private List<StudentEntity> studentEntities;
}
