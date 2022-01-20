package com.hlkj.mallclassic.vo;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @program: mall-classic
 * @description:
 * @author: 李向平
 * @create: 2021-03-31 18:05
 */
public class OrderVO {
    private String id;
    private String orderNo;
    private BigDecimal totalPrice;
    private BigDecimal finalTotalPrice;
    private Integer totalCount;
    private Timestamp expiredTime;
    private Timestamp placedTime;
    private String snapImg;
    private String snapTitle;
    private Byte status;
    private String userId;
    private long period;

    @Override
    public String toString() {
        return "OrderVO{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", totalPrice=" + totalPrice +
                ", finalTotalPrice=" + finalTotalPrice +
                ", totalCount=" + totalCount +
                ", expiredTime=" + expiredTime +
                ", placedTime=" + placedTime +
                ", snapImg='" + snapImg + '\'' +
                ", snapTitle='" + snapTitle + '\'' +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", period=" + period +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getFinalTotalPrice() {
        return finalTotalPrice;
    }

    public void setFinalTotalPrice(BigDecimal finalTotalPrice) {
        this.finalTotalPrice = finalTotalPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Timestamp getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Timestamp expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Timestamp getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(Timestamp placedTime) {
        this.placedTime = placedTime;
    }

    public String getSnapImg() {
        return snapImg;
    }

    public void setSnapImg(String snapImg) {
        this.snapImg = snapImg;
    }

    public String getSnapTitle() {
        return snapTitle;
    }

    public void setSnapTitle(String snapTitle) {
        this.snapTitle = snapTitle;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
