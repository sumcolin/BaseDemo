package com.joey.persistent.custom.sqlSession;

import com.joey.persistent.custom.pojo.Configuration;
import com.joey.persistent.custom.pojo.MappedStatement;

import java.lang.reflect.*;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String namespaceId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMapperStatementMap().get(namespaceId);
        return new SimpleExecutor().query(configuration, mappedStatement, params);
    }

    public <T> T selectOne(String namespaceId, Object... params) throws Exception {
        List<Object> list = selectList(namespaceId, params);
        if (list != null && list.size() == 1) {
            return (T) list.get(0);
        }
        return null;
    }

    public <T> T getMapper(Class<?> mapperClass) throws Exception {
        Object proxyInstance =  Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String key = className + "." + methodName;
                Type genericReturnType = method.getGenericReturnType();
                //判断是否实现泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    return selectList(key,args);
                }
                return selectOne(key,args);

            }
        });
        return (T) proxyInstance;
    }
}
