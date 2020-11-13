package com.joey.common.http;

import com.alibaba.fastjson.JSONObject;
import com.joey.common.utils.BoundUrl;
import okhttp3.*;

import java.util.Map;
import java.util.Set;


/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2020/11/12
 */
public class DefaultHttpRequest implements HttpRequest {

    private OkHttpClient client;

    private Request.Builder builder;

    public DefaultHttpRequest() {
        client = new OkHttpClient();
        builder = new Request.Builder();
    }

    public <T> T doGet(String url) {
        Request request = builder.url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) response;
    }

    public <T> T doGet(String url, Object paramsObj) {
        String boundUrl = "";
        try {
            boundUrl = boundUrl(url, paramsObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doGet(boundUrl);
    }

    public <T> T doPost(String url, Object obj) {
        Request request = builder.post((RequestBody) obj).url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) response;
    }

    public <T> T doPostJson(String url, Object paramObj) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String paramJson = JSONObject.toJSONString(paramObj);
        RequestBody requestBody = RequestBody.create(JSON, paramJson);
        return doPost(url, requestBody);
    }

    public <T> T doPostForm(String url, Map<Object, Object> paramsMap) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        Set<Object> keys = paramsMap.keySet();
        for (Object key : keys) {
            formBodyBuilder.add(key.toString(), paramsMap.get(key).toString());
        }
        FormBody formBody = formBodyBuilder.build();
        return doPost(url, formBody);
    }

    // 解析
    private String boundUrl(String url, Object paramObj) throws Exception {
        if (paramObj == null)
            return url;
        return BoundUrl.paramsUrl(url, paramObj);
    }

    public <T> T doDownload() {
        return null;
    }


    public <T> T doUpload() {
        return null;
    }
}
