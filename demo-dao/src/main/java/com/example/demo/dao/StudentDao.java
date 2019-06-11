package com.example.demo.dao;

import com.example.demo.dao.bean.BinaryObjectBean;
import com.example.demo.dao.bean.StudentBean;
import com.example.demo.dao.repo.StudentEntityRepository;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.entity.StudentEntity;
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
  private final BinaryObjectDao binaryObjectDao;

  @Autowired
  public StudentDao(StudentEntityRepository studentEntityRepository,BinaryObjectDao binaryObjectDao) {
    this.studentEntityRepository = studentEntityRepository;
    this.binaryObjectDao=binaryObjectDao;
  }

  @Transactional
  public StudentBean addOrUpdateStudent(StudentBean studentBean) {

    BinaryObjectBean portraitBean = studentBean.getPortraitBean();
    portraitBean=binaryObjectDao.saveBinaryObject(portraitBean);

    StudentEntity studentEntity =
            studentEntityRepository
                    .findById(studentBean.getId() == null ? 0L : studentBean.getId())
                    .orElseGet(() -> studentEntityRepository.newEntity());

    studentEntity.setName(studentBean.getName());
    studentEntity.setGender(Gender.valueOf(studentBean.getGender().name()));
    studentEntity.setPortraitId(portraitBean.getId());
    studentEntity = studentEntityRepository.saveAndFlush(studentEntity);

    studentBean.setId(studentEntity.getId());
    studentBean.setGender(studentEntity.getGender());
    studentBean.setName(studentEntity.getName());
    studentBean.getPortraitBean().setId(portraitBean.getId());
    return studentBean;
  }
}
