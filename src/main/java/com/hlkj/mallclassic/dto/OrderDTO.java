package com.hlkj.mallclassic.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

/**
 * @program: mall-classic
 * @description: 下单前端传输对象
 * @author: 李向平
 * @create: 2021-03-20 10:47
 */
public class OrderDTO {

    @DecimalMin(value = "0.00", message = "价格不合法")
    @DecimalMax(value = "99999999.99", message = "价格不合法")
    private BigDecimal totalPrice;
    private BigDecimal finalTotalPrice;

    private String couponId;//订单使用的优惠券（一个订单只能使用一个优惠券）
    private List<SkuInfoDTO> skuInfoList;
    private OrderAddressDTO orderAddressDTO;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "totalPrice=" + totalPrice +
                ", finalTotalPrice=" + finalTotalPrice +
                ", couponId='" + couponId + '\'' +
                ", skuInfoList=" + skuInfoList +
                ", orderAddressDTO=" + orderAddressDTO +
                '}';
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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public List<SkuInfoDTO> getSkuInfoList() {
        return skuInfoList;
    }

    public void setSkuInfoList(List<SkuInfoDTO> skuInfoList) {
        this.skuInfoList = skuInfoList;
    }

    public OrderAddressDTO getOrderAddressDTO() {
        return orderAddressDTO;
    }

    public void setOrderAddressDTO(OrderAddressDTO orderAddressDTO) {
        this.orderAddressDTO = orderAddressDTO;
    }
}
