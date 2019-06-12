package com.example.demo.dao.bean;

import com.example.demo.model.entity.StudentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StudentBean extends PersonBean implements Jsonable {


    public StudentBean(StudentEntity studentEntity){
        super(studentEntity.getId(),studentEntity.getName(),studentEntity.getGender(),BinaryObjectBean.builder().id(studentEntity.getPortraitId()).build(),studentEntity.getCreationDate().toEpochMilli());
    }
}
