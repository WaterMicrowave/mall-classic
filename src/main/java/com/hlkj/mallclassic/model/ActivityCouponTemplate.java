package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-03-22 19:44
 */
@Entity
@Table(name = "activity_coupon_template", schema = "hengshi-wechat", catalog = "")
public class ActivityCouponTemplate {
    @Id
    private String id;
    private String couponTempalteId;
    private int activityId;

    @Override
    public String toString() {
        return "ActivityCouponTemplate{" +
                "id='" + id + '\'' +
                ", couponTempalteId='" + couponTempalteId + '\'' +
                ", activityId=" + activityId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponTempalteId() {
        return couponTempalteId;
    }

    public void setCouponTempalteId(String couponTempalteId) {
        this.couponTempalteId = couponTempalteId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
