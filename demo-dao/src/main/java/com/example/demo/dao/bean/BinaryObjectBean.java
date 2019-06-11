package com.example.demo.dao.bean;

import com.example.demo.model.BinaryObjectEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : Sarah Xu
 * @date : 2019-05-22
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class BinaryObjectBean {
  private String id;
  private byte[] data;
  private String mimeType;
  private Long size;

  public BinaryObjectBean(BinaryObjectEntity binaryObjectEntity) {
    this.id = binaryObjectEntity.getId();
    this.data = binaryObjectEntity.getData();
    this.mimeType = binaryObjectEntity.getMimeType();
    this.size = binaryObjectEntity.getSize();
  }
  public BinaryObjectBean(MultipartFile file){
    try {
      this.data = file.getBytes();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.size=file.getSize();
    this.mimeType=file.getContentType();
  }
}
