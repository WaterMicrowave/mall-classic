package com.hlkj.mallclassic.enumerate;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.enumerate
 * @ClassName: LoginType
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 17:19
 * @Version: 1.0
 */
public enum LoginType {
    USER_WX(1,"微信登录"),
    ACCOUNT_PASSWORD(2,"账号密码登录"),
    MOBILE(3,"手机登录");

    private Integer value;
    private String description;

    LoginType(Integer value,String description){
        this.value = value;
        this.description = description;
    }
    public Integer getValue(){
        return this.value;
    }
}
