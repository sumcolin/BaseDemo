package com.joey.common.pojo;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2020/11/13
 */
public class TestObj implements Cloneable{

    Map<String,String> hashMap = new HashMap<String, String>();

    public static void main(String[] args) throws CloneNotSupportedException {

        String s = JSONObject.toJSONString(new Object());

        TestObj obj = new TestObj();
        Class<? extends TestObj> aClass = obj.getClass();
        Proxy proxy = (Proxy) Proxy.newProxyInstance(Object.class.getClassLoader(), new Class[]{TestObj.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

    }
}
