package com.joey.wx.model;

import lombok.Data;

@Data
public class EventMsg extends BaseMsg {

    // 事件通用参数
    private String event;

    private String msgID;

    private String status;

    // 自定义菜单事件参数
    private String eventKey;

    private String menuId;
}
