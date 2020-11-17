package com.joey.persistent.custom.sqlSession;

import com.joey.persistent.custom.pojo.Configuration;

import java.sql.Connection;

public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
