package com.example.demo.api.impl;

import com.example.demo.api.ResponseBean;
import com.example.demo.api.StudentApi;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.bean.PersonBean;
import com.example.demo.dao.bean.StudentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentApiImpl implements StudentApi {

  private final StudentDao studentDao;

  @Autowired
  public StudentApiImpl(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public ResponseBean<Void> createStudent(createStudentRequestBean input) {
    StudentBean studentBean;
    studentBean =
        StudentBean.builder()
            .name(input.getName())
            .gender(PersonBean.Gender.valueOf(input.getGender().toUpperCase()))
            .build();
    studentDao.addOrUpdateStudent(studentBean);
    return new ResponseBean<>();
  }
}
