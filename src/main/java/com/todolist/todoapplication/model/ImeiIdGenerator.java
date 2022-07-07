package com.todolist.todoapplication.model;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.Configurable;
import org.hibernate.type.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Component
public class ImeiIdGenerator implements IdentifierGenerator, Configurable {

    private String prefix;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        prefix = params.getProperty("prefix");
    }

    @Override
    public Serializable generate(
            SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException {
        return prefix + UUID.randomUUID();
    }

    @Override
    public void configure(Map map) {

    }
}
