package com.example.demo.api.impl;

import com.example.demo.api.ImageApi;
import com.example.demo.api.ResponseBean;
import com.example.demo.api.StudentApi;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.bean.BinaryObjectBean;
import com.example.demo.dao.bean.PersonBean;
import com.example.demo.dao.bean.StudentBean;
import com.example.demo.service.source.BinaryObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@Slf4j
public class ImageApiImpl implements ImageApi {

  private final BinaryObjectService binaryObjectService;
  @Autowired
  public ImageApiImpl(BinaryObjectService binaryObjectService){
    this.binaryObjectService=binaryObjectService;
  }


  @Override
  public ResponseBean<ImageApi.ImageResponseBean> getImage(String imageId) {
    BinaryObjectBean binaryObjectBean = binaryObjectService.getBinaryObject(imageId);
    return new ResponseBean<>(ImageApi.ImageResponseBean.builder().mimeType(binaryObjectBean.getMimeType()).resourceDataBytes(binaryObjectBean.getData()).resourceId(binaryObjectBean.getId()).build());
  }
}
