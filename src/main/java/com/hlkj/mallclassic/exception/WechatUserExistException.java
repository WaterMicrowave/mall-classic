package com.hlkj.mallclassic.exception;


import com.hlkj.mallclassic.configration.ExceptionCodeConfiguration;

public class WechatUserExistException extends HttpException {

    public WechatUserExistException() {
        ExceptionCodeConfiguration codeConfiguration = new ExceptionCodeConfiguration();
        this.code = 600005;
        this.message = codeConfiguration.getMessage(this.code);
        codeConfiguration = null;//方便GC回收
    }


}
