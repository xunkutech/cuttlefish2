package com.example.demo.dao.repo;

import com.example.demo.model.StudentCourseEntity;

import java.util.stream.Stream;

public interface StudentCourseEntityRepository extends BaseEntityRepository<StudentCourseEntity> {

  Stream<StudentCourseEntity> findByCourseEntityId(String courseId);
}
