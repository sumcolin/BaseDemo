package com.joey.wx.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class WxConfig {

    // 微信公众号ID
    @Value("${wechat.appid}")
    private String appid;

    // 微信公众号Secret
    @Value("${wechat.secret}")
    private String secret;

    // 微信公众号域名
    @Value("${wechat.domain.base}")
    private String domain;

    // 备用域名（熔灾处理）
    @Value("${wechat.domain.standby}")
    private String standbyDomain;

    // 微信公众号token,用于校验配置地址
    @Value("${wechat.token}")
    private String token;

    // 微信公众号账号ID
    @Value("${wechat.accountId}")
    private String accountId;

    // 网页授权token域名
    @Value("${wechat.login.token}")
    private String loginToken;
}
