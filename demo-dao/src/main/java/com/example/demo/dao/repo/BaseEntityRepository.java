package com.example.demo.dao.repo;

import com.example.demo.model.BaseEntity;
import net.jodah.typetools.TypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.UUID;

@NoRepositoryBean
public interface BaseEntityRepository<E extends BaseEntity>
    extends JpaRepository<E, String>, JpaSpecificationExecutor<E> {

  String SYS_ID = "00000001";
  String MACHINE_ID = "00000001";

  default String genUuid() {
    ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
    byteBuffer.putLong(System.currentTimeMillis());
    return new StringBuffer(SYS_ID)
        .append(MACHINE_ID)
        .append(DatatypeConverter.printHexBinary(byteBuffer.array()).toLowerCase())
        .append(UUID.randomUUID().toString().replace("-", ""))
        .toString();
  }

  @SuppressWarnings("unchecked")
  default E newEntity() {
    Type repoType =
        ((Class<? extends BaseEntityRepository<E>>) this.getClass().getGenericInterfaces()[0])
            .getGenericInterfaces()[0];

    Class<E> entityClass =
        (Class<E>) TypeResolver.resolveRawArgument(repoType, BaseEntityRepository.class);
    try {
      E e = entityClass.getConstructor().newInstance();
      e.setId(genUuid());
      return e;
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Query(
      " UPDATE #{#entityName} "
          + "SET activeStatus= "
          + "CASE activeStatus "
          + "WHEN TRUE THEN FALSE "
          + "WHEN FALSE THEN TRUE "
          + "ELSE activeStatus "
          + "END "
          + "WHERE id=?1 ")
  @Modifying(clearAutomatically = true)
  int toggleActiveStatus(String id);

  /**
   * Override hard delete to soft delete
   *
   * @param id
   * @return
   */
  @Query(
      " UPDATE #{#entityName} " + "SET activeStatus=false " + "WHERE id=?1 AND activeStatus=TRUE ")
  @Modifying(clearAutomatically = true)
  int deactiveStatus(String id);
}
