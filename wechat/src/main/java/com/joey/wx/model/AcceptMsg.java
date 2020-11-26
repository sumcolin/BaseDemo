package com.joey.wx.model;

import lombok.Data;

@Data
public class AcceptMsg {

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

    // 事件通用参数
    private String event;

    private String msgID;

    private String status;

    // 自定义菜单事件参数
    private String eventKey;

    private String menuId;
}
