package com.joey.common.http;

import java.io.IOException;
import java.util.Map;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2020/11/12
 */
public interface HttpRequest {

    // 无参请求
    public <T> T doGet(String url) throws IOException;

    // 有参请求
    public <T> T doGet(String url, Object paramsObj);

    public <T> T doPostJson(String url, Object paramsMap);

    public <T> T doPostForm(String url, Map<Object,Object> paramsMap);

    // 下载文件
    public <T> T doDownload();

    // 上传文件
    public <T> T doUpload();
}
