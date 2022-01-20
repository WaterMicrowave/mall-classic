package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-03-17 14:54
 */
@Entity
@Table(name = "coupon_category", schema = "hengshi-wechat", catalog = "")
public class CouponCategory {
    private String id;
    private String categoryId;
    private String couponId;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_id", nullable = false, length = 32)
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "coupon_id", nullable = false, length = 32)
    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponCategory that = (CouponCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(couponId, that.couponId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, couponId);
    }
}
