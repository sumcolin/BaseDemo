package com.joey.common.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2020/11/13
 */
public class TestHttp {

    public static void main(String[] args) throws Exception {

        OkHttpClient client = new OkHttpClient();

        Request request = new  Request.Builder().build();

        client.newCall(request).execute();

    }
}
