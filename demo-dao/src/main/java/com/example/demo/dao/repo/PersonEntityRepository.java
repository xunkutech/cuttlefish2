package com.example.demo.dao.repo;

import com.example.demo.model.entity.PersonEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonEntityRepository<E extends PersonEntity> extends BaseEntityRepository<E> {

  Optional<E> findOptionalByName(String name);
}
