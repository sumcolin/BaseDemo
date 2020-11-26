package com.joey.wx.builder;


import com.joey.wx.model.AcceptMsg;

// 处理接受的消息
public class DefaultWechatAccept implements Wechat{

    private AcceptMsg acceptMsg;

    public DefaultWechatAccept(AcceptMsg acceptMsg) {
        this.acceptMsg = acceptMsg;
    }

    @Override
    public void handle() {
        String msgType = acceptMsg.getMsgType();
    }
}
