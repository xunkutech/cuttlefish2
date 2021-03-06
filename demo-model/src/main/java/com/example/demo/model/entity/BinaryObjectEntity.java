package com.example.demo.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "demo_binary_object")
@NoArgsConstructor
public class BinaryObjectEntity extends BaseEntity implements Serializable {

  private static final long serialVersionUID = 2566549826321750380L;

  // 文件大小
  @Column(name = "size", nullable = false, columnDefinition = "BIGINT")
  private Long size;

  @Column(name = "mime_type", length = 64, columnDefinition = "VARCHAR(64)")
  private String mimeType;

  @Lob
  @Column(name = "data")
  private byte[] data;



  @Builder(toBuilder = true)
  public BinaryObjectEntity(Long id, byte[] data, String mimeType, Long size) {
    this.id = id;
    this.data = data;
    this.mimeType = mimeType;
    this.size=size;
  }
}
