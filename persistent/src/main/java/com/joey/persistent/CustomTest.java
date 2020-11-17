package com.joey.persistent;

import com.joey.persistent.custom.io.Resource;

import java.io.InputStream;

public class CustomTest {


    public static void main(String[] args) {
        // 1、获取资源文件流
        InputStream inputStream = Resource.getResourceAsStream("sqlMapConfig.xml");

    }
}
