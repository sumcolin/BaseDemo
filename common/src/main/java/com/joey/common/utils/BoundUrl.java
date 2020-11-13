package com.joey.common.utils;

import java.lang.reflect.Field;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2020/11/13
 */
public class BoundUrl {

    // 解析连接
    public static String paramsUrl(String url, Object paramsObj) throws Exception {
        String[] urls = url.split("\\?");
        String newUrl = urls[0];
        if (urls.length == 2) {
            StringBuffer paramsUrl = new StringBuffer("?");
            Class<?> paramClass = paramsObj.getClass();
            String[] params = urls[1].split("\\&");
            for (String param : params) {
                String[] split = param.split("\\=");
                String k = split[0];
                Field declaredField = paramClass.getDeclaredField(k);
                declaredField.setAccessible(true);
                String v = declaredField.get(paramsObj).toString();
                paramsUrl.append(k + "=" + v + "&");
            }
            newUrl += paramsUrl.substring(0, paramsUrl.length() - 1);
        }
        return newUrl;
    }
}
