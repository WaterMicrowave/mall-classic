package com.hlkj.mallclassic.model;

import com.hlkj.mallclassic.enumerate.OrderStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: mall-classic
 * @description: 订单实体
 * @author: 李向平
 * @create: 2021-03-20 10:42
 */
@Entity
@Table(name = "[order]", schema = "hengshi-wechat", catalog = "")
public class Order {
    @Id
    private String id;
    private String orderNo;
    private BigDecimal totalPrice;
    private Integer totalCount;
    @Column(name = "create_time", insertable = false, updatable = false)
    private Timestamp createTime;
    private Timestamp deleteTime;
    private Timestamp expiredTime;
    private Timestamp placedTime;
    @Column(name = "update_time", insertable = false, updatable = false)
    private Timestamp updateTime;
    private String snapImg;
    private String snapTitle;
    private String snapItems;
    private String snapAddress;
    private String prepayId;
    private BigDecimal finalTotalPrice;
    private Byte status;
    private String userId;

    public Boolean needCancel(){
        //可能CMS取消订单
        if (!OrderStatus.toType(this.getStatus().intValue()).equals(OrderStatus.UNPAID)){
            return true;
        }
        //订单可能已过期（消息通知未触发导致）
        long nowTime = new Date().getTime();
        if(nowTime > this.getExpiredTime().getTime()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", userId='" + userId + '\'' +
                ", totalPrice=" + totalPrice +
                ", totalCount=" + totalCount +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", expiredTime=" + expiredTime +
                ", placedTime=" + placedTime +
                ", updateTime=" + updateTime +
                ", snapImg='" + snapImg + '\'' +
                ", snapTitle='" + snapTitle + '\'' +
                ", snapItems='" + snapItems + '\'' +
                ", snapAddress='" + snapAddress + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", finalTotalPrice=" + finalTotalPrice +
                ", status=" + status +
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    public String getSnapItems() {
        return snapItems;
    }

    public void setSnapItems(String snapItems) {
        this.snapItems = snapItems;
    }

    public String getSnapAddress() {
        return snapAddress;
    }

    public void setSnapAddress(String snapAddress) {
        this.snapAddress = snapAddress;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public BigDecimal getFinalTotalPrice() {
        return finalTotalPrice;
    }

    public void setFinalTotalPrice(BigDecimal finalTotalPrice) {
        this.finalTotalPrice = finalTotalPrice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
