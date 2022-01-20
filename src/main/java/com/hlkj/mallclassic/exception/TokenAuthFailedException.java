package com.hlkj.mallclassic.exception;

public class TokenAuthFailedException extends HttpException {

    public TokenAuthFailedException() {
        this.code = 600001;
    }
}
