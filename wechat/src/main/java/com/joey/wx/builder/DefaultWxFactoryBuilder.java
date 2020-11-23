package com.joey.wx.builder;


public class DefaultWxFactoryBuilder implements WxFactoryBuilder{

    @Override
    public Wechat openWx(Wechat wechat) {
        return wechat;
    }
}
