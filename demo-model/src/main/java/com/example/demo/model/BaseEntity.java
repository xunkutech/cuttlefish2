package com.example.demo.model;

import com.example.demo.converter.BooleanStringConverter;
import com.example.demo.converter.InstantConverter;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  /* Primary key */
  @Id
  @Column(
      name = "id",
      unique = true,
      nullable = false,
      updatable = false,
      length = 64)
  protected String id;

  /* Soft delete flag */
  @Column(name = "active_status", nullable = false)
  @Convert(converter = BooleanStringConverter.class)
  protected Boolean activeStatus = true;

  @CreatedBy
  @Column(
          name = "created_by",
          nullable = false,
          length = 64)
  protected String createdBy = "";

  @CreatedDate
  @Column(name = "creation_date", nullable = false)
  @Convert(converter = InstantConverter.class)
  protected Instant creationDate = Instant.now();

  @LastModifiedBy
  @Column(
      name = "last_updated_by",
      nullable = false,
      length = 64)
  protected String lastUpdatedBy = "";;

  @LastModifiedDate
  @Column(name = "last_update_date", nullable = false)
  @Convert(converter = InstantConverter.class)
  protected Instant lastUpdateDate = Instant.now();;
}
