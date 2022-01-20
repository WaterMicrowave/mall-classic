package com.hlkj.mallclassic.exception;


import com.hlkj.mallclassic.configration.ExceptionCodeConfiguration;

public class UsernameOrPwdException extends HttpException {

    public UsernameOrPwdException() {
        ExceptionCodeConfiguration codeConfiguration = new ExceptionCodeConfiguration();
        this.code = 600004;
        this.message = codeConfiguration.getMessage(this.code);
        codeConfiguration = null;//方便GC回收
    }


}
