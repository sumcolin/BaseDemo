package com.joey.persistent.custom.dao;

import com.joey.persistent.pojo.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> selectList();

    public Student selectOne(Student student);

}
