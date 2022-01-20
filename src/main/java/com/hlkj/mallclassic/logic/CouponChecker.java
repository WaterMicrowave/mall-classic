package com.hlkj.mallclassic.logic;

import com.hlkj.mallclassic.bo.SkuOrderBO;
import com.hlkj.mallclassic.core.money.IMoneyDiscount;
import com.hlkj.mallclassic.enumerate.CouponType;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.ActivityCouponException;
import com.hlkj.mallclassic.exception.OrderException;
import com.hlkj.mallclassic.model.Coupon;
import com.hlkj.mallclassic.utils.CommonUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: mall-classic
 * @description: 优惠券校验逻辑
 *  由于CouponChecker不能是单例的，所以不应该由springboot托管.这里使用纯净CouponChecker对象。
 * @author: 李向平
 * @create: 2021-03-21 11:56
 */
public class CouponChecker {

    private Coupon coupon;
    private IMoneyDiscount iMoneyDiscount;
    private List<String> couponEffectCategoryIds;//优惠券使用的商品分类id列表

    public CouponChecker(Coupon coupon,List<String> couponEffectCategoryIds, IMoneyDiscount iMoneyDiscount) {
        this.coupon = coupon;
        this.couponEffectCategoryIds = couponEffectCategoryIds;
        this.iMoneyDiscount = iMoneyDiscount;
    }
    /**
     * 优惠券是否过期(优惠券的status字段不可靠，须使用时间卡点进行判断)
     */
    public void idOk(){
        Boolean inTimeLine = CommonUtils.isInTimeLine(new Date(), this.coupon.getStartTime(), this.coupon.getEndTime());
        if(!inTimeLine){
            throw new ActivityCouponException(500008);
        }
    }
    /**
     * 最终价格校验
     * @param orderFinalTotalPrice
     * @param severTotalPrice
     */
    public void finalTotalPriceIsOk(BigDecimal orderFinalTotalPrice,
                                    BigDecimal severTotalPrice,
                                    List<SkuOrderBO> skuOrderBOList){
        BigDecimal serverFinalTotalPrice;
        switch (CouponType.toType(this.coupon.getType())){
            case FULL_MINUS://满减券
                serverFinalTotalPrice = severTotalPrice.subtract(this.coupon.getMinus());
                break;
            case FULL_OFF://满减折扣券
                BigDecimal categoryTotalPrice = this.getSumByCategoryList(skuOrderBOList, this.couponEffectCategoryIds);
                serverFinalTotalPrice = severTotalPrice.subtract(this.iMoneyDiscount.discount(categoryTotalPrice, this.coupon.getRate()));
                break;
            case NO_THRESHOLD_MINUS://无门槛减除券
                serverFinalTotalPrice = severTotalPrice.subtract(this.coupon.getMinus());
                //要注意无门槛券可能导致支付金额出现负数的情况(建议前端加限制)
                if(serverFinalTotalPrice.compareTo(new BigDecimal("0")) <= 0){
                    throw new ActivityCouponException(500010);//无门槛券不能在当前订单中使用
                }
                break;
            default:
                throw new APIParamException();
        }
        //前后端最终价格对比
        int compare = serverFinalTotalPrice.compareTo(orderFinalTotalPrice);
        if(compare!=0){
            throw new OrderException(800004);//订单最终总价校验失败
        }
    }
    /**
     * 校验当前订单中的优惠券是否达到使用条件（优惠券可使用的商品分类）
     * @param skuOrderBOList
     * @param serverTotalPrice
     */
    public void canBeUsed(List<SkuOrderBO> skuOrderBOList, BigDecimal serverTotalPrice){
        //1、计算当前订单中，可使用优惠券优惠的所有商品的消费金额
        BigDecimal orderCategoryPrice = new BigDecimal("0");
        if (coupon.getWholeStore()){//全场券，无品类限制
            orderCategoryPrice = orderCategoryPrice.add(serverTotalPrice);
        }
        else{//计算优惠券优惠品类下所有商品的价格总和
            orderCategoryPrice = orderCategoryPrice.add(this.getSumByCategoryList(skuOrderBOList,this.couponEffectCategoryIds));
        }
        //2、判断当前订单是否可以使用优惠券
        this.couponCanBeUed(orderCategoryPrice);
    }

    //优惠券是否达到使用条件：满减券有最小消费金额限制
    private void couponCanBeUed(BigDecimal orderCategoryPrice){
        switch (CouponType.toType(coupon.getType())){
            case FULL_OFF://满减折扣券
            case FULL_MINUS://满减券
                int compare = this.coupon.getFullMoney().compareTo(orderCategoryPrice);
                if (compare>0){
                    throw new ActivityCouponException(500009);//订单中商品未达到优惠券使用条件
                }
                break;
            case NO_THRESHOLD_MINUS:
                break;
            default:
                throw new APIParamException();
        }
    }
    //计算优惠券在当前订单中作用的所有分类下商品的总价
    private BigDecimal getSumByCategoryList(List<SkuOrderBO> skuOrderBOList, List<String> categoryIds){
        return categoryIds.stream()
                .map(cid -> this.getSumByCategory(skuOrderBOList, cid))
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
    }

    //计算优惠券在当前订单中的指定一个分类下商品的总价
    private BigDecimal getSumByCategory(List<SkuOrderBO> skuOrderBOList, String categoryId){
        return skuOrderBOList.stream()
                .filter(skuOrderBO -> skuOrderBO.getCategoryId().equals(categoryId))
                .map(bo -> bo.getTotalPrice())
                .reduce(BigDecimal::add)
                .orElse(new BigDecimal("0"));
    }


    /*getter setter toString*/
    /*getter setter toString*/
    public Coupon getCoupon() {
        return coupon;
    }
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
    public IMoneyDiscount getiMoneyDiscount() {
        return iMoneyDiscount;
    }
    public void setiMoneyDiscount(IMoneyDiscount iMoneyDiscount) {
        this.iMoneyDiscount = iMoneyDiscount;
    }
    public List<String> getCouponEffectCategoryIds() {
        return couponEffectCategoryIds;
    }
    public void setCouponEffectCategoryIds(List<String> couponEffectCategoryIds) {
        this.couponEffectCategoryIds = couponEffectCategoryIds;
    }
}
