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
/**
* @Description: 根据id获取二进制文件
* @Param: [objectId]
* @return: com.example.demo.dao.bean.BinaryObjectBean
* @Author: Sarah Xu
* @Date: 2019/6/12
*/
  public BinaryObjectBean getBinaryObjectByID(Long objectId){
    return new BinaryObjectBean(binaryObjectRepository.findById(objectId).orElseThrow(()->new CommonException(ErrorCode.OBJECT_NOT_FOUND_OR_DELETED)));
  }
  /**
  * @Description: 保存二进制文件
  * @Param: [binaryObjectBean]
  * @return: com.example.demo.dao.bean.BinaryObjectBean
  * @Author: Sarah Xu
  * @Date: 2019/6/12
  */
  public BinaryObjectBean saveBinaryObject(BinaryObjectBean binaryObjectBean){

      binaryObjectBean = new BinaryObjectBean(binaryObjectRepository.saveAndFlush(binaryObjectRepository.newEntity().toBuilder().data(binaryObjectBean.getData()).mimeType(binaryObjectBean.getMimeType()).size(binaryObjectBean.getSize()).build()));
    return  binaryObjectBean;
  }



}
