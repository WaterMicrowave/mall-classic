package com.hlkj.mallclassic.exception;

public class ForbiddenException extends HttpException {

    public ForbiddenException() {
        this.code = 600003;
    }
}