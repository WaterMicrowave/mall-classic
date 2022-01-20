package com.hlkj.mallclassic.vo;

import com.hlkj.mallclassic.model.Activity;

/**
 * @program: mall-classic
 * @description: 用于前端优惠券列表展示
 * @author: 李向平
 * @create: 2021-03-22 18:24
 */
public class ActivityCouponCategoryVO {
    private PureCouponVO pureCouponVO;
    private Activity activity;
    private boolean collected;//是否已领取

    public PureCouponVO getPureCouponVO() {
        return pureCouponVO;
    }

    public void setPureCouponVO(PureCouponVO pureCouponVO) {
        this.pureCouponVO = pureCouponVO;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
