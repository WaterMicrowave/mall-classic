package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Coupon;
import com.hlkj.mallclassic.model.CouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: CouponCategoryRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface CouponCategoryRepository extends JpaRepository<CouponCategory, String> {

    @Query("select cc.categoryId from CouponCategory cc where cc.couponId=:couponId")
    List<String> findIdsByCouponId(@Param("couponId") String couponId);

    @Query("select cc.categoryId from CouponCategory cc where cc.couponId=:couponId")
    List<String> findCategoryIdsByCouponId(@Param("couponId") String couponId);
}
