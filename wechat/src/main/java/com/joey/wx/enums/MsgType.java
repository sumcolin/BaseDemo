package com.joey.wx.enums;

/**
 * 消息类型
 */
public enum MsgType {

    // 事件通知
    EVENT("event"),

    // 文本消息
    TEXT("text");

    private String value;

    MsgType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
