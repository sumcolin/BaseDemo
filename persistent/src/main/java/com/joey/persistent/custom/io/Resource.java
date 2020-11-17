package com.joey.persistent.custom.io;

import java.io.InputStream;

/**
 * 读取资源文件方式
 * 1、通过class获取
 * 2、通过类加载器 classLoader
 * getResourceAsStream方法
 */
public class Resource {

    // 资源文件读取
    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
