package com.hlkj.mallclassic.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Coupon;
import com.hlkj.mallclassic.model.UserCoupon;
import com.hlkj.mallclassic.repository.CouponRepository;
import com.hlkj.mallclassic.repository.UserCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

@Component
public class Scheduler{

    @Autowired
    private UserCouponRepository userCouponRepository;
    @Autowired
    private CouponRepository couponRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每隔5秒执行一次
//    @Scheduled(fixedRate = 60000)
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }

    /**
     * 凌晨更新用户"已过期"的优惠券
     */
    @Scheduled(cron = "0 10 13 ? * *")
    public void taskExpiredUpdate() {
        System.out.println("优惠券过期更新：" + dateFormat.format(new Date()));
        this.judgeCouponExpired();
    }

    private void judgeCouponExpired(){
        //1、获取所有"已领用,未使用"的优惠券列表
        List<UserCoupon> userCoupons = userCouponRepository.expireJudgeList();
        if (ListUtils.isEmpty(userCoupons)) {
            throw new NotFoundException();
        }
        //2、筛选出已过期的优惠券列表
        ArrayList<String> updatingCouponsIds = new ArrayList<>();
        for (UserCoupon uc:
             userCoupons) {
            String couponId = uc.getCouponId();
            Coupon coupon = couponRepository.findById(couponId).orElseThrow(NotFoundException::new);
            //2-1判断优惠券可使用截止时间
            if(coupon.getEndTime().getTime() <= new Date().getTime()){
                updatingCouponsIds.add(coupon.getId());
            }
        }
        //3、更新已过期的优惠券列表
//        userCouponRepository.updateExpired(updatingCouponsIds);
    }

}