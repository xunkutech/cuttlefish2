package com.example.demo.dao;

import com.example.demo.dao.bean.StudentBean;
import com.example.demo.model.enums.Gender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotEquals;

/**
 * @Author: fate
 * @Date: 2019/5/16 11:39
 * @Version 1.0
 */
public class StudentDaoTest extends BaseTest{
    @Autowired
    StudentDao studentDao;

    StudentBean studentBean;

    @Before
    public void setUp(){
        studentBean = StudentBean.builder()
                .name("BOB")
                .gender(Gender.MALE)
                .build();
        studentBean.setName("BOB");
        studentBean.setGender(Gender.MALE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addOrUpdateStudent() {
        StudentBean studentBean = studentDao.addOrUpdateStudent(this.studentBean);
        assertNotEquals(studentBean.getId(),null);
    }
}