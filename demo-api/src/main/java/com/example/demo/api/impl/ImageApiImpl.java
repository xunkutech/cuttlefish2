package com.example.demo.api.impl;

import com.example.demo.api.ImageApi;
import com.example.demo.api.bean.ResponseBean;
import com.example.demo.dao.bean.BinaryObjectBean;
import com.example.demo.service.source.BinaryObjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class ImageApiImpl implements ImageApi {

  private final BinaryObjectService binaryObjectService;

  @Autowired
  public ImageApiImpl(BinaryObjectService binaryObjectService) {
    this.binaryObjectService = binaryObjectService;
  }

  @Override
  public ResponseBean<ImageResponseBean> getImageAsBase64(String imageId) {
    BinaryObjectBean binaryObjectBean = binaryObjectService.getBinaryObject(imageId);
    return new ResponseBean<>(
        ImageApi.ImageResponseBean.builder()
            .mimeType(binaryObjectBean.getMimeType())
            .resourceDataBytes(binaryObjectBean.getData())
            .resourceId(binaryObjectBean.getId())
            .build());
  }

  @Override
  public ResponseBean<ImageResponseBean> getImageAsStream(
      @PathVariable("id") String imageId, HttpServletResponse response) {
    BinaryObjectBean binaryObjectBean = binaryObjectService.getBinaryObject(imageId);
    response.setContentType(binaryObjectBean.getMimeType());
    try (ServletOutputStream out = response.getOutputStream(); ) {
      out.write(binaryObjectBean.getData());
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new ResponseBean<>(new ImageResponseBean(123L, "img/jpg", null));
  }
}
