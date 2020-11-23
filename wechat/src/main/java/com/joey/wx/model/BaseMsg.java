package com.joey.wx.model;


import lombok.Data;

@Data
public class BaseMsg {

    // 接受者
    private String toUserName;

    // 发送者
    private String fromUserName;

    // 创建时间
    private String createTime;

    // 消息类型
    private String msgType;

    // 消息详情
    private String content;


}
