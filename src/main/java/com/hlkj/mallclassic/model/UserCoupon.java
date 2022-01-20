package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-03-17 14:54
 */
@Entity
@Table(name = "user_coupon", schema = "hengshi-wechat", catalog = "")
public class UserCoupon {
    @Id
    private String id;
    private String userId;
    private String couponId;
    private String orderId;
    private Integer status;
    private Date createTime;

    @Override
    public String toString() {
        return "UserCoupon{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", couponId='" + couponId + '\'' +
                ", status=" + status +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
