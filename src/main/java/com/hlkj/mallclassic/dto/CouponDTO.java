package com.hlkj.mallclassic.dto;

import java.io.Serializable;

/**
 * @program: mall-classic
 * @description: 优惠券领取界面
 * @author: 李向平
 * @create: 2021-03-24 18:13
 */
public class CouponDTO implements Serializable {
    private String activityId;
    private String couponId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
}
