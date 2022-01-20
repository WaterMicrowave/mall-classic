package com.hlkj.mallclassic.vo;

import com.hlkj.mallclassic.enumerate.CouponType;
import com.hlkj.mallclassic.model.Category;
import com.hlkj.mallclassic.model.Coupon;
import com.hlkj.mallclassic.repository.CategoryRepository;

import java.util.List;

/**
 * @program: mall-classic
 * @description: 活动优惠券列表展示时使用
 * @author: 李向平
 * @create: 2021-03-22 18:49
 */
public class PureCouponVO {
    private Coupon coupon;
    private String effectCategories;

    public PureCouponVO(Coupon coupon, List<Category> categories) {
        StringBuilder sb = new StringBuilder();
        if(coupon.getType() == CouponType.NO_THRESHOLD_MINUS.getValue()){
            sb.append("无门槛 全场通用");
        }else{
            categories.forEach(c -> {
                if(sb.length()==0){
                    sb.append(c.getName());
                }else{
                    sb.append(","+c.getName());
                }
            });
        }
        this.coupon = coupon;
        this.effectCategories = sb.toString();
    }

    @Override
    public String toString() {
        return "PureCouponVO{" +
                "coupon=" + coupon +
                ", effectCategoryIds='" + effectCategories + '\'' +
                '}';
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public String getEffectCategoryIds() {
        return effectCategories;
    }

}
