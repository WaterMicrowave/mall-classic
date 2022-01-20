package com.hlkj.mallclassic.exception;

public class HttpException extends RuntimeException{

    protected Integer code=500;//在exception-config.properties
    protected String message="服务器异常";

    public HttpException() {
    }
    public HttpException(int code) {
        this.code = code;
    }
    public HttpException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}