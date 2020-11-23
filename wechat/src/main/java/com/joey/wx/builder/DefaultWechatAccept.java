package com.joey.wx.builder;

import com.joey.wx.handler.MsgHandler;

import com.joey.wx.model.BaseMsg;
import com.joey.wx.model.EventMsg;
import org.apache.commons.lang3.StringUtils;

// 处理接受的消息
public class DefaultWechatAccept implements Wechat{

    private Object currentMsg;

    public DefaultWechatAccept(Object currentMsg) {
        this.currentMsg = currentMsg;
    }

    @Override
    public void handle() {
        EventMsg currentMsg = (EventMsg) this.currentMsg;
        String content = currentMsg.getContent();
        if (StringUtils.isBlank(content)){
            // 处理事件的消息
            new MsgHandler(currentMsg);
        }else if(StringUtils.isNotBlank(content)){
            // 处理文本消息
            BaseMsg baseMsg = (BaseMsg) currentMsg;

        }
    }
}
