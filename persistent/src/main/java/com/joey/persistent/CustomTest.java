package com.joey.persistent;

import com.joey.persistent.custom.io.Resource;
import com.joey.persistent.custom.sqlSession.SqlSession;
import com.joey.persistent.custom.sqlSession.SqlSessionFactory;
import com.joey.persistent.custom.sqlSession.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class CustomTest {


    public static void main(String[] args) throws Exception{
        // 1、获取资源文件流
        InputStream inputStream = Resource.getResourceAsStream("sqlMapConfig.xml");
        // 2、创建sqlSessionFactory 工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3、获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        sqlSession.selectList("")
    }
}
