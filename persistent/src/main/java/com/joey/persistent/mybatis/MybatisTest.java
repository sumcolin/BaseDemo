package com.joey.persistent.mybatis;

import com.joey.persistent.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void test1() throws Exception{
        // 加载配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        // 创建工厂
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 创建sqlSession
        SqlSession sqlSession = build.openSession();
        // 获取值
        List<Student> list = sqlSession.selectList("userMapper.findAll");
        //打印结果
        System.out.println(list);
        sqlSession.close();

    }

    @Test
    public void test2() throws Exception{

        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/SqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();

        Student student = new Student();
        student.setId(5);
        student.setName("carrie");
        student.setAge(3);
        sqlSession.insert("studentMapper.saveStudent", student);
        sqlSession.commit();

        sqlSession.close();


    }
}
