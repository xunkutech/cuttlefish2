package com.example.demo.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author : Sarah Xu
 * @date : 2019-05-14
 **/
public class UUIDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String id = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        id = id.replace("-", "");
        return id;
    }
}