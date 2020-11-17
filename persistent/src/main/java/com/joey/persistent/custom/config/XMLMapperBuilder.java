package com.joey.persistent.custom.config;

import com.joey.persistent.custom.pojo.Configuration;
import com.joey.persistent.custom.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws Exception {

        // 解析mapper文件
        Document document = new SAXReader().read(inputStream);
        // 获取根节点
        Element rootElement = document.getRootElement();
        // 组建Key值
        String namespace = rootElement.attributeValue("namespace");

        // 存储查询语句
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String parameterType = element.attributeValue("parameterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sqlText);
            String key = namespace + "." + id;
            configuration.getMapperStatementMap().put(key, mappedStatement);
        }


    }
}
