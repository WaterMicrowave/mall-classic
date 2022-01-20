package com.hlkj.mallclassic.exception;


import com.hlkj.mallclassic.configration.ExceptionCodeConfiguration;

/**
 * 活动（优惠券）异常
 */
public class ActivityCouponException extends HttpException {

    public ActivityCouponException(int code) {
        ExceptionCodeConfiguration codeConfiguration = new ExceptionCodeConfiguration();
        this.code = code;
        this.message = codeConfiguration.getMessage(this.code);
        codeConfiguration = null;//方便GC回收
    }


}
