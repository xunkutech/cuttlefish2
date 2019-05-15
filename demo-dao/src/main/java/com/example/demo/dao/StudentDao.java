package com.example.demo.dao;

import com.example.demo.dao.bean.StudentBean;
import com.example.demo.dao.repo.StudentEntityRepository;
import com.example.demo.enums.Gender;
import com.example.demo.model.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(
    readOnly = true,
    rollbackFor = {DataAccessException.class})
public class StudentDao extends AbstractDao {

  private final StudentEntityRepository studentEntityRepository;

  @Autowired
  public StudentDao(StudentEntityRepository studentEntityRepository) {
    this.studentEntityRepository = studentEntityRepository;
  }

  @Transactional
  public StudentBean addOrUpdateStudent(StudentBean studentBean) {
    StudentEntity studentEntity =
        studentEntityRepository
            .findById(studentBean.getId())
            .orElseGet(() -> studentEntityRepository.newEntity());

    studentEntity.setName(studentBean.getName());
    studentEntity.setGender(Gender.valueOf(studentBean.getGender().name()));
    studentEntity = studentEntityRepository.saveAndFlush(studentEntity);
    studentBean.setId(studentEntity.getId());
    return studentBean;
  }
}
