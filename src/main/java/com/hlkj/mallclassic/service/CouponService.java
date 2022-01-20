package com.hlkj.mallclassic.service;

import com.hlkj.mallclassic.common.UnifyResponse;
import com.hlkj.mallclassic.core.LocalUser;
import com.hlkj.mallclassic.enumerate.CouponStatus;
import com.hlkj.mallclassic.enumerate.CouponType;
import com.hlkj.mallclassic.exception.ActivityCouponException;
import com.hlkj.mallclassic.exception.NotFoundException;
import com.hlkj.mallclassic.exception.SuccessException;
import com.hlkj.mallclassic.model.*;
import com.hlkj.mallclassic.repository.*;
import com.hlkj.mallclassic.utils.CommonUtils;
import com.hlkj.mallclassic.utils.UUIDUtils;
import com.hlkj.mallclassic.vo.ActivityCouponCategoryVO;
import com.hlkj.mallclassic.vo.CategoryVO;
import com.hlkj.mallclassic.vo.CouponCategoryVO;
import com.hlkj.mallclassic.vo.PureCouponVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.service
 * @ClassName: BannerService
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:43
 * @Version: 1.0
 */
@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponCategoryRepository couponCategoryRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserCouponRepository userCouponRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 获取所有优惠券（优惠券列表页面展示）：条件如下
     *  活动：存在（未删除）&未下线线&未结束
     *  优惠券：使用戒指时间未到
     *  用户未领取
     * @return
     */
    public List<ActivityCouponCategoryVO> getAllNormal(){
        List<ActivityCouponCategoryVO> resultList = new ArrayList<>();
        //1、获取所有优惠券（活动未下线&活动未结束&优惠券使用时间未截至）
        List<Coupon> allCoupons = couponRepository.findAllNormal(new Timestamp(new Date().getTime()));
        if(!ListUtils.isEmpty(allCoupons)){
            for (Coupon coupon:
                 allCoupons) {
                //2、判断活动 是否存在&未下线&未结束//todo 未删除！！
                Optional<Activity> aOptional = activityRepository.findById(coupon.getActivityId());
                if(!aOptional.isPresent()){
                    throw new ActivityCouponException(500001);//活动不存在
                }
                Activity activity = aOptional.get();
                //3、组装优惠券可使用的分类（字符串展示）
                List<String> categoryIds = couponCategoryRepository.findCategoryIdsByCouponId(coupon.getId());
                List<Category> categories = new ArrayList<>();
                if(coupon.getType() == CouponType.NO_THRESHOLD_MINUS.getValue()){
                    //全场通用
                }else{
                    categoryIds.forEach(c -> {
                        Category category = categoryRepository.findById(c).get();
                        if (!categories.contains(category)){
                            categories.add(category);
                        }
                    });
                }
                //4、组装数据
                ActivityCouponCategoryVO accVO = new ActivityCouponCategoryVO();
                PureCouponVO pureCouponVO = new PureCouponVO(coupon, categories);
                accVO.setActivity(activity);
                accVO.setPureCouponVO(pureCouponVO);
                //添加到结果
                resultList.add(accVO);
            }
        }
        //返回
        return resultList;
    }

    /**
     * 用户领取优惠券。
     * @param activityId 活动id
     * @param couponId 优惠券id
     * @param userId 用户id
     * @return
     */
    public UnifyResponse collect(String activityId, String couponId, String userId){
        Date now = new Date();
        /**
         * 判断活动是否有效：存在&未下线&时间未截至
         */
        //时间（当前时间大于活动开始时间，且小于结束时间）
        Optional<Activity> optional = activityRepository.findById(activityId);
        if(!optional.isPresent()){
            throw new ActivityCouponException(500001);
        }
        //是否online
        if(optional.get().getOnline() == 0){
            throw new ActivityCouponException(500002);
        }
        //是否未过期
        if(optional.get().getEndTime().getTime() < now.getTime()){
            throw new ActivityCouponException(500003);
        }
        /**
         * 判断优惠券是否有效：存在即可。截至时间本系统未考虑（用户下单时会筛选）。
         */
        Coupon coupon = null;
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        if(!optionalCoupon.isPresent()){
            throw new ActivityCouponException(500004);
        }
        coupon = optionalCoupon.get();
        /**
         * 判断用户是否已领取优惠券
         */
        userCouponRepository.findByUserIdEqualsAndCouponIdEquals(userId, couponId)
                .ifPresent((uc) -> {throw new ActivityCouponException(500006);});
        //写入数据库
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(UUIDUtils.generateUUID());
        userCoupon.setCouponId(couponId);
        userCoupon.setStatus(CouponStatus.AVAILABLE.getValue());
        userCoupon.setUserId(userId);
        userCoupon.setCreateTime(now);
        userCouponRepository.save(userCoupon);

        return UnifyResponse.buildSuccess();
    }

    /** 获取所有‘已领取，可以使用’的优惠券 */
    public List<Coupon> getMyAvailableCoupons(String uid){
        Timestamp now = new Timestamp(new Date().getTime());
        return couponRepository.getByAvailable(uid, now);
    }
    /** 获取所有‘已使用’的优惠券 */
    public List<Coupon> getMyUsedCoupons(String uid){
        Timestamp now = new Timestamp(new Date().getTime());
        return couponRepository.getByUsed(uid, now);
    }
    /** 获取所有‘已过期，未使用’的优惠券 */
    public List<Coupon> getMyExpiredCoupons(String uid){
        Timestamp now = new Timestamp(new Date().getTime());
        return couponRepository.getByExpired(uid, now);
    }

    /**
     * 获取用户可用的优惠券列表（带分类——下单时使用）
     * @param uid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public List<CouponCategoryVO> getAvailableCouponWithCategory(String uid) throws InvocationTargetException, IllegalAccessException {
        List<CouponCategoryVO> result = new ArrayList<>();
        //1、获取当前可用的优惠券
        List<Coupon> coupons = this.getMyAvailableCoupons(uid);
        if(coupons.isEmpty()){
            throw new NotFoundException();
        }
        //2、组装返回数据
        return this.packageCouponCategoryVO(coupons);
    }

    /**
     * 获取我的优惠券
     * @param uid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public List<CouponCategoryVO> getMyselfAll(String uid) throws InvocationTargetException, IllegalAccessException {
        List<CouponCategoryVO> result = new ArrayList<>();
        //1、获取当前可用的优惠券
        List<Coupon> availableCoupons = this.getMyAvailableCoupons(uid);
        if (!ListUtils.isEmpty(availableCoupons)){
            List<CouponCategoryVO> available = this.packageCouponCategoryVO(availableCoupons);
            available.forEach(couponCategoryVO -> {
                couponCategoryVO.setStatus(1);
            });
            result.addAll(available);
        }
        //2、获取当前已使用的优惠券
        List<Coupon> usedCoupons = this.getMyUsedCoupons(uid);
        if (!ListUtils.isEmpty(usedCoupons)) {
            List<CouponCategoryVO> used = this.packageCouponCategoryVO(usedCoupons);
            used.forEach(couponCategoryVO -> {
                couponCategoryVO.setStatus(2);
            });
            result.addAll(used);
        }
        //3、获取当前已过期的优惠券
        List<Coupon> expiredCoupons = this.getMyExpiredCoupons(uid);
        if(!ListUtils.isEmpty(expiredCoupons)){
            List<CouponCategoryVO> expired = this.packageCouponCategoryVO(expiredCoupons);
            expired.forEach(couponCategoryVO -> {
                couponCategoryVO.setStatus(3);
            });
            result.addAll(expired);
        }
        //返回
        return result;
    }

    private List<CouponCategoryVO> packageCouponCategoryVO(List<Coupon> coupons) throws InvocationTargetException, IllegalAccessException {
        List<CouponCategoryVO> result = new ArrayList<>();
        for (Coupon c:
                coupons) {
            CouponCategoryVO couponCategoryVO = new CouponCategoryVO();
            List<String> categoryIs = couponCategoryRepository.findCategoryIdsByCouponId(c.getId());
            List<CategoryVO> categoryVOS = new ArrayList<>();
            if(!categoryIs.isEmpty()){
                for (String categoryId :
                        categoryIs) {
                    Category category  = categoryRepository.findById(categoryId).orElseThrow(NotFoundException::new);
                    CategoryVO categoryVO = new CategoryVO();
                    BeanUtils.copyProperties(categoryVO, category);
                    categoryVOS.add(categoryVO);
                }
            }
            couponCategoryVO.setCoupon(c);
            couponCategoryVO.setCategories(categoryVOS);
            //添加到返回结果
            result.add(couponCategoryVO);
        }
        return result;
    }

}
