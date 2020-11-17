package com.joey.persistent.custom.sqlSession;

import java.util.List;

public interface SqlSession {

    public <E> List<E> selectList(String namespaceId,Object... params) throws Exception;

    public <T> T selectOne(String namespaceId,Object... params) throws Exception;


    public <T> T getMapper(Class<?> mapperClass) throws Exception;
}
