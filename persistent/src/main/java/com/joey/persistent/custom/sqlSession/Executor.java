package com.joey.persistent.custom.sqlSession;

import com.joey.persistent.custom.pojo.Configuration;
import com.joey.persistent.custom.pojo.MappedStatement;

import java.util.List;

public interface Executor {

    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
