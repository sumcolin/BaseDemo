package com.joey.wx.enums;

/**
 * 微信消息事件触发类型
 */
public enum EventType {

    // 查看事件（菜单栏）
    VIEW("VIEW"),

    // 点击事件（菜单栏）
    CLICK("CLICK"),

    // 关注事件通知
    SUBSCRIBE("subscribe"),

    // 模版消息返回通知
    TEMPLATE("SENDJOBFINISH");

    private String value ;

    EventType(String value) {
        this.value = value;
    }

    public static EventType valueOf(){
        return null;
    }

    public String getValue() {
        return value;
    }
}
