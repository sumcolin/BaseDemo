package com.joey.wx.builder;

import com.joey.wx.config.WxConfig;

public class DefaultWechatSend implements Wechat{

    // 微信配置文件
    private WxConfig wxConfig;

    public DefaultWechatSend(WxConfig wxConfig) {
        this.wxConfig = wxConfig;
    }

    @Override
    public void handle() {

    }
}
