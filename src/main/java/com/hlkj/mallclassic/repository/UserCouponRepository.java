package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: UserCouponRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface UserCouponRepository extends JpaRepository<UserCoupon, String> {

    Optional<List<UserCoupon>> findByUserIdEqualsAndCouponIdEquals(String userId, String couponId);

    //查询当前用户拥有的所有优惠券（和状态无关）
    List<UserCoupon> findAllByUserIdEqualsAndCouponIdIn(String uid,List<String> couponIds);

    //查询所有"已领取"状态的优惠券列表
    @Query(nativeQuery = true, value = "select * from user_coupon where status=1")
    List<UserCoupon> expireJudgeList();

    //核销优惠券
    @Modifying
    @Transactional
    @Query(value = "update UserCoupon uc\n" +
            "set uc.status=2, uc.orderId=:orderId \n" +
            "where uc.userId=:uid \n" +
            "and uc.couponId =:couponId\n" +
            "and uc.status = 1\n" +
            "and uc.orderId is  null ")
    int writeOff(String uid,String couponId, String orderId);

}
