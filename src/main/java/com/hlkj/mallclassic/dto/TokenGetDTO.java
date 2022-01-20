package com.hlkj.mallclassic.dto;

import com.hlkj.mallclassic.dto.validators.TokenPassword;
import com.hlkj.mallclassic.enumerate.LoginType;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @description: 登录获取token前端传递的对象
 * @author:李向平
 * @create: 2020-11-29 15:40
 */
public class TokenGetDTO implements Serializable {
    @NotBlank(message = "账号不能为空")
    private String account;//微信登录-code，其他-用户名
    @TokenPassword(min = 6,max = 30,message = "密码不合规范")
    private String password;//账号+密码 登录
    private LoginType loginType;//登录方式
    private String iv;//微信登录
    private String encryptedData;//微信登录

    @Override
    public String toString() {
        return "TokenGetVO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", loginType=" + loginType +
                ", iv='" + iv + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }
}