package com.example.demo.dao;

import com.example.demo.dao.bean.BinaryObjectBean;
import com.example.demo.dao.exception.CommonException;
import com.example.demo.dao.exception.ErrorCode;
import com.example.demo.dao.repo.BinaryObjectRepository;
import com.example.demo.model.entity.BinaryObjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(
    readOnly = true,
    rollbackFor = {DataAccessException.class})
public class BinaryObjectDao extends AbstractDao {

  @Autowired
  BinaryObjectRepository binaryObjectRepository;

  public BinaryObjectBean getBinaryObjectByID(String objectId){
    return new BinaryObjectBean(binaryObjectRepository.findById(objectId).orElseThrow(()->new CommonException(ErrorCode.OBJECT_NOT_FOUND_OR_DELETED)));
  }
  public BinaryObjectBean saveBinaryObject(BinaryObjectBean binaryObjectBean){

      binaryObjectBean = new BinaryObjectBean(binaryObjectRepository.saveAndFlush(BinaryObjectEntity.builder().data(binaryObjectBean.getData()).mimeType(binaryObjectBean.getMimeType()).size(binaryObjectBean.getSize()).build()));
    return  binaryObjectBean;
  }



}
