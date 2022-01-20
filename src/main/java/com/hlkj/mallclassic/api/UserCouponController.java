package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.dto.CouponDTO;
import com.hlkj.mallclassic.service.UserCouponService;
import com.hlkj.mallclassic.vo.PureUserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @program: mall-classic
 * @description:
 * @author: 李向平
 * @create: 2021-03-24 18:45
 */
@RestController
@RequestMapping("/usercoupon")
public class UserCouponController {

    @Autowired
    private UserCouponService userCouponService;

    @RequestMapping("/collected")
    @ScopeLevel
    public UnifyResponse getAllByUserIdCouponsIds(@RequestBody List<CouponDTO> couponDTOList){
        List<PureUserCoupon> result = userCouponService.getAllByUserIdCouponsIds(couponDTOList);
        return UnifyResponse.buildSuccess(result);
    }
}
