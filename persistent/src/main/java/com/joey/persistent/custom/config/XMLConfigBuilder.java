package com.joey.persistent.custom.config;

import com.joey.persistent.custom.io.Resource;
import com.joey.persistent.custom.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws Exception {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        // 存放配置信息
        Properties properties = new Properties();
        // 遍历属性值
        List<Element> list = rootElement.selectNodes("//property");
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        // 配置数据库连接信息
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(properties.getProperty("driverClass"));
        dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        dataSource.setUser(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);

        // 获取其他配置信息
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String resource = element.attributeValue("resource");
            InputStream mapperInputStream = Resource.getResourceAsStream(resource);
            new XMLMapperBuilder(configuration).parse(mapperInputStream);
        }
        return configuration;
    }
}
