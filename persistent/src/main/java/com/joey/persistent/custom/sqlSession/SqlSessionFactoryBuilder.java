package com.joey.persistent.custom.sqlSession;

import com.joey.persistent.custom.config.XMLConfigBuilder;
import com.joey.persistent.custom.pojo.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {


    public SqlSessionFactory build(InputStream inputStream) throws Exception{
        // 解析文件
        Configuration configuration = new XMLConfigBuilder().parseConfig(inputStream);
        // 创建sqlSessionFactory工厂类
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
