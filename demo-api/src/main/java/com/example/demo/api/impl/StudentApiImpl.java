package com.example.demo.api.impl;

import com.example.demo.api.StudentApi;
import com.example.demo.api.bean.ResponseBean;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.bean.BinaryObjectBean;
import com.example.demo.dao.bean.StudentBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentApiImpl implements StudentApi {

  private final StudentDao studentDao;

  @Autowired
  public StudentApiImpl(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public ResponseBean<Void> createStudent(CreateStudentRequestBean input) {
    StudentBean studentBean;
    studentBean =
        StudentBean.builder()
            .name(input.getName())
            .gender(input.getGender())
                .portraitBean(new BinaryObjectBean(input.getPortrait()))
            .build();
    studentDao.addOrUpdateStudent(studentBean);
    return new ResponseBean<>();
  }


}
