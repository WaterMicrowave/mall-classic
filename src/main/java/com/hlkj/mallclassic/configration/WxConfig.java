package com.hlkj.mallclassic.configration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信相关配置
 */
@ConfigurationProperties(prefix = "wx.config")
@Component
public class WxConfig {

    //唯一标识
    private String appid;
    //秘钥
    private String appsercret;

    @Override
    public String toString() {
        return "WxPayConfig{" +
                "appid='" + appid + '\'' +
                ", appsercret='" + appsercret + '\'' +
                '}';
    }

    public String getAppid() {

        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsercret() {
        return appsercret;
    }

    public void setAppsercret(String appsercret) {
        this.appsercret = appsercret;
    }
}

