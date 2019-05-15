package com.example.demo.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(
    readOnly = true,
    rollbackFor = {DataAccessException.class})
public class TeacherDao extends AbstractDao {}
