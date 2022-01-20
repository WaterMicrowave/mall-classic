package com.hlkj.mallclassic.logic;

import com.hlkj.mallclassic.bo.SkuOrderBO;
import com.hlkj.mallclassic.dto.OrderDTO;
import com.hlkj.mallclassic.dto.SkuInfoDTO;
import com.hlkj.mallclassic.exception.APIParamException;
import com.hlkj.mallclassic.exception.OrderException;
import com.hlkj.mallclassic.exception.SpuOrSkuException;
import com.hlkj.mallclassic.model.Sku;
import com.hlkj.mallclassic.model.Spu;
import org.thymeleaf.util.ListUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mall-classic
 * @description: 内部调用CouponChecker
 *  调用链(外观模式：facade)：OrderService -> OrderChecker -> CouponChecker
 * @author: 李向平
 * @create: 2021-03-21 17:47
 */
public class OrderChecker {

    private OrderDTO orderDTO;//前端传递的订单信息
    private List<Spu> spuList;//服务端查询的单品列表
    private List<Sku> skuList;//服务端查询的多个个商品列表
    private CouponChecker couponChecker;//优惠券校验器
    private Integer maxLimit;

    public String getLeaderImg(){
        StringBuilder joiner = new StringBuilder();
        List<Spu> spuList = this.getSpuList();
        List<Sku> skuList = this.getSkuList();
        if (!ListUtils.isEmpty(spuList)) {
            spuList.forEach(spu -> {
                if (joiner.toString().length()==0){
                    joiner.append(spu.getImg());
                }
                else{
                    joiner.append("|" + spu.getImg());
                }
            });
        }
        if (!ListUtils.isEmpty(skuList)) {
            skuList.forEach(sku -> {
                if (joiner.toString().length()==0){
                    joiner.append(sku.getImg());
                }
                else{
                    joiner.append("," + sku.getImg());
                }
            });
        }
        return joiner.toString();
    }
    public String getLeaderTitle(){
        StringBuilder joiner = new StringBuilder();
        List<Spu> spuList = this.getSpuList();
        List<Sku> skuList = this.getSkuList();
        if (!ListUtils.isEmpty(spuList)) {
            spuList.forEach(spu -> {
                if (joiner.toString().length()==0){
                    joiner.append(spu.getTitle());
                }
                else{
                    joiner.append("," + spu.getTitle());
                }
            });
        }
        if (!ListUtils.isEmpty(skuList)) {
            skuList.forEach(sku -> {
                if (joiner.toString().length()==0){
                    joiner.append(sku.getTitle());
                }
                else{
                    joiner.append("," + sku.getTitle());
                }
            });
        }
        return joiner.toString();
    }

    public OrderChecker(OrderDTO orderDTO,
                        List<Spu> spuList,
                        List<Sku> skuList,
                        CouponChecker couponChecker,
                        Integer maxLimit) {
        this.orderDTO = orderDTO;
        this.spuList = spuList;
        this.skuList = skuList;
        this.couponChecker = couponChecker;
        this.maxLimit = maxLimit;
    }

    /**
     * 校验点：
     *  1、商品是否已下架或删除
     *  2、商品是否已售罄（这里价值不大。建议在入库时检查）
     *  3、数量超出库存校验（超卖）
     *  4、订单商品（单件）数量限制校验
     *  5、前端传递的总价和服务端计算的总价
     *  6、优惠券校验（调用CouponChecker）
     */
    public void orderVerify(){
        //定义服务端计算总价格
        BigDecimal serverTotalPrice = new BigDecimal("0");
        ArrayList<SkuOrderBO> skuOrderBOList = new ArrayList<>();
        //1、判断订单中商品是否有下架或删除的
        this.allSpuOrSkuOnSale(orderDTO.getSkuInfoList().size(),this.skuList.size()+this.spuList.size());
        //2、判断订单中是否存在售罄的商品
        spuList.forEach(spu -> this.containsSoldOut(false, spu, null));
        skuList.forEach(sku -> this.containsSoldOut(true, null, sku));
        //3、判断订单中是否存在超卖的商品
        List<SkuInfoDTO> skuInfoList = orderDTO.getSkuInfoList();
        for (SkuInfoDTO skuInfoDTO:
             skuInfoList) {
            if(!skuInfoDTO.getHasSku()){
                for (Spu spu:
                        spuList) {
                    if(skuInfoDTO.getId().equals(spu.getId())){
                        this.beyondStock(skuInfoDTO,false,spu,null);
                    }
                }
            }
            else if(skuInfoDTO.getHasSku()){
                for (Sku sku:
                        skuList) {
                    if(skuInfoDTO.getId().equals(sku.getId())){
                        this.beyondStock(skuInfoDTO,true,null,sku);
                    }
                }
            }
        }
        //4、判断订单中单个商品数量是否超过最大限制
        skuInfoList.forEach(skuInfoDTO -> this.beyondMaxLimit(skuInfoDTO));

        //5、计算总价格
        for (SkuInfoDTO skuInfoDTO:
                skuInfoList) {
            if (!skuInfoDTO.getHasSku()) {//单品
                for (Spu spu:
                        spuList) {
                    if(skuInfoDTO.getId().equals(spu.getId())){
                        BigDecimal bigDecimal = this.calculateSpuOrSkuTotalPrice(false, spu, null, skuInfoDTO);
                        serverTotalPrice = serverTotalPrice.add(bigDecimal);
                    }
                }
            }
            if (skuInfoDTO.getHasSku()) {//多规格
                for (Sku sku:
                        skuList) {
                    if(skuInfoDTO.getId().equals(sku.getId())){
                        BigDecimal bigDecimal = this.calculateSpuOrSkuTotalPrice(true, null, sku, skuInfoDTO);
                        serverTotalPrice = serverTotalPrice.add(bigDecimal);
                    }
                }
            }
        }
        //判断总价是否一致
        this.totalPriceIsOk(orderDTO.getTotalPrice(), serverTotalPrice);
        //6、优惠券校验
        // 组装skuOrderBOList
        for (SkuInfoDTO skuInfoDTO:
                skuInfoList) {
            if(!skuInfoDTO.getHasSku()){
                for (Spu spu:
                        spuList) {
                    if(skuInfoDTO.getId().equals(spu.getId())){
                        SkuOrderBO skuOrderBO = new SkuOrderBO(false, spu, null, skuInfoDTO);
                        skuOrderBOList.add(skuOrderBO);
                    }
                }
            }
            if(skuInfoDTO.getHasSku()){
                for (Sku sku:
                        skuList) {
                    if(skuInfoDTO.getId().equals(sku.getId())){
                        SkuOrderBO skuOrderBO = new SkuOrderBO(true, null, sku, skuInfoDTO);
                        skuOrderBOList.add(skuOrderBO);
                    }
                }
            }
        }
        //如果使用了优惠券，需要进行优惠券校验
        if (this.couponChecker!=null){
            couponChecker.idOk();//优惠券是否过期
            couponChecker.canBeUsed(skuOrderBOList,serverTotalPrice);//校验当前订单中的优惠券是否达到使用条件
            couponChecker.finalTotalPriceIsOk(orderDTO.getFinalTotalPrice(), serverTotalPrice, skuOrderBOList);//最终价格校验
        }
    }

    //比较前端传递的订单总价和后端计算的是否一样
    private void totalPriceIsOk(BigDecimal totalPrice, BigDecimal serverTotalPrice){
        if(totalPrice.compareTo(serverTotalPrice) !=0){
            throw new OrderException(800003);
        }
    }

    //计算单个商品的总价格
    private BigDecimal calculateSpuOrSkuTotalPrice(boolean hasSku, Spu spu, Sku sku, SkuInfoDTO skuInfoDTO){
        if(skuInfoDTO.getCount()==0){
            throw new SpuOrSkuException(700007);
        }
        if(hasSku){
            return sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
        }else{
            return spu.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
        }
    }

    //商品是否未下架且未删除
    private void allSpuOrSkuOnSale(int count1, int count2){
        if (count1!=count2){
            throw new SpuOrSkuException(700003);
        }
    }
    //商品是否售罄(预判断！可能在下一个瞬间变为0)
    private void containsSoldOut(boolean hasSku, Spu spu,Sku sku){
        if (hasSku){
            if(sku.getStock() == 0){
                throw new SpuOrSkuException(700004);
            }
        }
        else {
            if(spu.getStock() == 0){
                throw new SpuOrSkuException(700004);
            }
        }
    }
    //商品是否存在超卖（订单中商品数量大于库存数量）
    private void beyondStock(SkuInfoDTO skuInfoDTO, boolean hasSku, Spu spu, Sku sku){
        if (hasSku){
            if(skuInfoDTO.getCount() > sku.getStock()){
                throw new SpuOrSkuException(700005);
            }
        }
        else {
            if(skuInfoDTO.getCount() > spu.getStock()){
                throw new SpuOrSkuException(700005);
            }
        }
    }
    //商品购买数量是否超过系统限制可以购买的数量
    private void beyondMaxLimit(SkuInfoDTO skuInfoDTO){
        if (skuInfoDTO.getCount() > this.maxLimit){
            throw new SpuOrSkuException(700006);
        }
    }


    //getter setter
    //getter setter
    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public List<Spu> getSpuList() {
        return spuList;
    }

    public void setSpuList(List<Spu> spuList) {
        this.spuList = spuList;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    public CouponChecker getCouponChecker() {
        return couponChecker;
    }

    public void setCouponChecker(CouponChecker couponChecker) {
        this.couponChecker = couponChecker;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }
}
