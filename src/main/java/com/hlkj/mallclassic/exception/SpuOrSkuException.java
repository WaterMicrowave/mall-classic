package com.hlkj.mallclassic.exception;


import com.hlkj.mallclassic.configration.ExceptionCodeConfiguration;

/**
 * 商品（单品&多规格）异常
 */
public class SpuOrSkuException extends HttpException {

    public SpuOrSkuException(int code) {
        ExceptionCodeConfiguration codeConfiguration = new ExceptionCodeConfiguration();
        this.code = code;
        this.message = codeConfiguration.getMessage(this.code);
        codeConfiguration = null;//方便GC回收
    }


}
