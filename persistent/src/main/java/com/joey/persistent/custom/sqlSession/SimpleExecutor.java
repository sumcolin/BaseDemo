package com.joey.persistent.custom.sqlSession;

import com.joey.persistent.custom.config.BoundSql;
import com.joey.persistent.custom.pojo.Configuration;
import com.joey.persistent.custom.pojo.MappedStatement;
import com.joey.persistent.custom.util.GenericTokenParser;
import com.joey.persistent.custom.util.ParameterMapping;
import com.joey.persistent.custom.util.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行器
 */
public class SimpleExecutor implements Executor {


    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {

        // 1、获取连接
        Connection connection = configuration.getDataSource().getConnection();

        // 2、获取sql、转换sql 封装转换的参数
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 3、设置参数
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);

        List<ParameterMapping> parameterMappingsList = boundSql.getParameterMappingsList();
        if (parameterMappingsList.size() != 0) {
            for (int i = 0; i < parameterMappingsList.size(); i++) {
                ParameterMapping parameterMapping = parameterMappingsList.get(i);
                String content = parameterMapping.getContent();

                Field declaredField = parameterTypeClass.getDeclaredField(content);
                declaredField.setAccessible(true);
                Object o = declaredField.get(params[0]);

                preparedStatement.setObject(i + 1, o);
            }
        }

        // 4、设置返回参数
        ResultSet resultSet = preparedStatement.executeQuery();

        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);


        ArrayList<Object> objects = new ArrayList<Object>();
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();

            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);

                // 使用反射或者内省，根据数据库表和实体关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,value);
            }
            objects.add(o);
        }

        return (List<E>) objects;
    }


    private Class<?> getClassType(String parameterType) throws Exception {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }


    private BoundSql getBoundSql(String sql) {
        // 标记处理类: 配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        // 解析出来的结果
        String parseSql = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql();
        boundSql.setSqlText(parseSql);
        boundSql.setParameterMappingsList(parameterMappings);
        return boundSql;
    }
}
