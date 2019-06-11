package com.example.demo.service.source;

import com.example.demo.dao.BinaryObjectDao;
import com.example.demo.dao.bean.BinaryObjectBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Sarah Xu
 * @date : 2019-06-10
 */
@Service
public class BinaryObjectService {
  private BinaryObjectDao binaryObjectDao;

  @Autowired
  public BinaryObjectService(BinaryObjectDao binaryObjectDao) {
    this.binaryObjectDao = binaryObjectDao;
  }
  /**
   * @Description: 获取二进制对象 @Param: [objectId]
   * @return: com.example.demo.dao.bean.BinaryObjectBean
   * @Author: Sarah Xu @Date: 2019/6/10
   */
  public BinaryObjectBean getBinaryObject(String objectId) {
    return binaryObjectDao.getBinaryObjectByID(Long.valueOf(objectId));
  }
}
