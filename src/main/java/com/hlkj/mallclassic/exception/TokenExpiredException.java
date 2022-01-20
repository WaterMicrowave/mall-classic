package com.hlkj.mallclassic.exception;

public class TokenExpiredException extends HttpException {

    public TokenExpiredException() {
        this.code = 600002;
    }
}
