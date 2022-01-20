package com.hlkj.mallclassic.repository;

import com.hlkj.mallclassic.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.repository
 * @ClassName: BannerRepository
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:41
 * @Version: 1.0
 */
public interface CouponRepository extends JpaRepository<Coupon, String> {

    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id = uc.couponId\n" +
            "join User u\n" +
            "on u.id = uc.userId\n" +
            "where uc.status = 1 \n" +
            "and u.id = :uid\n" +
            "and c.startTime < :now\n" +
            "and c.endTime > :now\n" +
            "and uc.orderId is null\n" +
            "order by c.endTime asc")
    List<Coupon> getByAvailable(String uid, Timestamp now);

    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id = uc.couponId\n" +
            "join User u\n" +
            "on u.id = uc.userId\n" +
            "where uc.status = 2 \n" +
            "and u.id = :uid\n" +
            "and c.startTime < :now\n" +
            "and uc.orderId is not null")
    List<Coupon> getByUsed(String uid, Timestamp now);

    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id = uc.couponId\n" +
            "join User u\n" +
            "on u.id = uc.userId\n" +
            "where uc.status <>2 \n" +
            "and u.id = :uid\n" +
            "and c.endTime < :now\n" +
            "and uc.orderId is null")
    List<Coupon> getByExpired(String uid, Timestamp now);

    /**
     * 查询 活动存在&未下线&未结束 & 优惠券截止时间未到 的所有优惠券
     * @param now
     * @return
     */
    @Query("select c from Coupon c\n" +
            "join Activity a\n" +
            "on c.activityId = a.id\n" +
            "where a.online = 1\n" +
            "and a.endTime > :now \n" +
            "and c.endTime > :now \n" +
            " order by c.endTime asc, a.endTime asc ")
    List<Coupon> findAllNormal(Timestamp now);

}
