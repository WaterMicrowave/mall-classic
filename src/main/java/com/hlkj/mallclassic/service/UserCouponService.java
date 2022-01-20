package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.dto.CouponDTO;
import com.hlkj.mallclassic.exception.ActivityCouponException;
import com.hlkj.mallclassic.model.Activity;
import com.hlkj.mallclassic.model.User;
import com.hlkj.mallclassic.model.UserCoupon;
import com.hlkj.mallclassic.repository.ActivityRepository;
import com.hlkj.mallclassic.repository.CouponRepository;
import com.hlkj.mallclassic.repository.UserCouponRepository;
import com.hlkj.mallclassic.vo.ActivityCouponCategoryVO;
import com.hlkj.mallclassic.vo.PureUserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @program: mall-classic
 * @description:
 * @author: 李向平
 * @create: 2021-03-24 18:07
 */
@Service
public class UserCouponService {

    @Autowired
    private UserCouponRepository userCouponRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private CouponRepository couponRepository;

    /**
     * 查询当前用户已领取的优惠券列表。优惠券领取界面使用。
     * @param couponDTOList
     * @return
     */
    public List<PureUserCoupon> getAllByUserIdCouponsIds(List<CouponDTO> couponDTOList){
        List<PureUserCoupon> resultList = new ArrayList<>();
        String uid = LocalUser.getUser().getId();

        //所有优惠券的id列表
        List<String> couponIds = couponDTOList.stream()
                .map(couponDTO -> {
                    return couponDTO.getCouponId();
                }).collect(Collectors.toList());
        //查询当前用户所有领取的优惠券（和状态无关）
        List<UserCoupon> allUserCoupons = userCouponRepository.findAllByUserIdEqualsAndCouponIdIn(uid,couponIds);
        //判断用户是否已领取
        if (!ListUtils.isEmpty(couponDTOList)) {
            couponDTOList.forEach(couponDTO -> {
                if (!ListUtils.isEmpty(allUserCoupons)) {
                    allUserCoupons.forEach(uc -> {
                        String couponIdCollected = uc.getCouponId();//用户领取的优惠券id
                        String couponIdForward = couponDTO.getCouponId();//前端传递的优惠券id
                        String activityId = couponDTO.getActivityId();
                        //
                        if(couponIdCollected.equals(couponIdForward)){
                            //组装响应对象
                            PureUserCoupon pureUserCoupon = new PureUserCoupon();
                            pureUserCoupon.setActivityId(activityId);
                            pureUserCoupon.setCouponId(couponIdForward);
                            //追加到结果list
                            if(!resultList.contains(pureUserCoupon)){
                                resultList.add(pureUserCoupon);
                            }
                        }
                    });
                }
            });
        }
        return resultList;
    }

}
