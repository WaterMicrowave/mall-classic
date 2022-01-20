package com.hlkj.mallclassic.api;

import com.hlkj.mallclassic.annotations.ScopeLevel;
import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.enumerate.CouponStatus;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.model.Category;
import com.hlkj.mallclassic.model.Coupon;
import com.hlkj.mallclassic.model.User;
import com.hlkj.mallclassic.repository.CategoryRepository;
import com.hlkj.mallclassic.repository.CouponCategoryRepository;
import com.hlkj.mallclassic.service.CouponService;
import com.hlkj.mallclassic.vo.ActivityCouponCategoryVO;
import com.hlkj.mallclassic.vo.CategoryVO;
import com.hlkj.mallclassic.vo.CouponCategoryVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.api
 * @ClassName: CouponConytroller
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/15 10:20
 * @Version: 1.0
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponCategoryRepository couponCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 获取所有未结束的优惠券（//todo 由于数据表无delete_time字段，删除的未做判断）
     * @return
     */
    @RequestMapping("/all")
    public UnifyResponse getAllCoupons(){
        List<ActivityCouponCategoryVO> allNormal = couponService.getAllNormal();
        return UnifyResponse.buildSuccess(allNormal);
    }

    /**
     * 用户领取优惠券
     * @param activityId 优惠券所在活动id
     * @param couponId 优惠券id
     * @return
     */
    @ScopeLevel
    @RequestMapping("/collect/{activityId}/{couponId}")
    public UnifyResponse collectCoupon(@PathVariable String activityId,
                                       @PathVariable String couponId){
        //用户id不能显式传递到服务端（超权）
        User user = LocalUser.getUser();
//        Integer scope = LocalUser.getScope();
        return couponService.collect(activityId, couponId, user.getId());
    }

    /**
     * 获取我的优惠券（已领取未使用、已使用、已过期已过期）
     * @param status
     * @return
     */
    @ScopeLevel
    @RequestMapping("/myself/status/{status}")
    public UnifyResponse getMyselfCoupons(@PathVariable Integer status){
        String uid = LocalUser.getUser().getId();
        List<Coupon> couponList;
        switch (CouponStatus.toType(status)){
            case AVAILABLE:
                couponList = couponService.getMyAvailableCoupons(uid);
                break;
            case USED:
                couponList = couponService.getMyUsedCoupons(uid);
                break;
            case EXPIRED:
                couponList = couponService.getMyExpiredCoupons(uid);
                break;
            default:
                throw new APIParamException();
        }
        return UnifyResponse.buildSuccess(couponList);
    }

    /**
     * 获取用户可用的优惠券（带分类信息，在下单时使用）
     * @return
     */
    @RequestMapping("/myself/available/with_category")
    @ScopeLevel
    public UnifyResponse getAvailableCouponsWithCategory() throws InvocationTargetException, IllegalAccessException {
        String uid = LocalUser.getUser().getId();
        List<CouponCategoryVO> result = couponService.getAvailableCouponWithCategory(uid);
        return UnifyResponse.buildSuccess(result);
    }

    /**
     * 获取用户可用的优惠券（带分类信息，在下单时使用）
     * @return
     */
    @RequestMapping("/myself/all")
    @ScopeLevel
    public UnifyResponse getMyselfAll() throws InvocationTargetException, IllegalAccessException {
        String uid = LocalUser.getUser().getId();
        List<CouponCategoryVO> result = couponService.getMyselfAll(uid);
        return UnifyResponse.buildSuccess(result);
    }


}
