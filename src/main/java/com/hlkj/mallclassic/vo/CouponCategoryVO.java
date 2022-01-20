package com.hlkj.mallclassic.vo;

import com.hlkj.mallclassic.model.Coupon;

import java.util.List;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-03-19 15:49
 */
public class CouponCategoryVO {
    private Coupon coupon;//当前优惠券
    private List<CategoryVO> categories;//可使用的分类
    private Integer status=null;//仅在"我的优惠券"赋值

    @Override
    public String toString() {
        return "CouponCategoryVO{" +
                "coupon=" + coupon +
                ", categories=" + categories +
                ", status=" + status +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Coupon getCoupon() {

        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public List<CategoryVO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryVO> categories) {
        this.categories = categories;
    }
}
