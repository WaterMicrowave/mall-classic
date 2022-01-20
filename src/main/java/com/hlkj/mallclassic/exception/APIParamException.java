package com.hlkj.mallclassic.exception;


import com.hlkj.mallclassic.configration.ExceptionCodeConfiguration;

public class APIParamException extends HttpException {

    public APIParamException() {
        ExceptionCodeConfiguration codeConfiguration = new ExceptionCodeConfiguration();
        this.code = 400001;
        this.message = codeConfiguration.getMessage(this.code);
        codeConfiguration = null;//方便GC回收
    }


}
