package com.example.demo.dao.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Data
@SuperBuilder
public class TeacherBean extends PersonBean implements Jsonable {}
