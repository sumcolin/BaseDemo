package com.joey.persistent.custom.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 配置容器对象 （用来存储配置信息）
 */
public class Configuration {

    // 数据源
    private DataSource dataSource;

    // 存储sql数据
    private Map<String,MappedStatement> mapperStatementMap = new HashMap<String, MappedStatement>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MappedStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }
}
