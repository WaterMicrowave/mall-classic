package com.hlkj.mallclassic.enumerate;

import java.util.stream.Stream;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.enumerate
 * @ClassName: LoginType
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 17:19
 * @Version: 1.0
 */
public enum OrderStatus {
    ALL(0,"全部"),
    UNPAID(1,"待支付"),
    PAID(2,"已支付，待发货 "),
    DELIVERED(3,"已发货，待收货"),
    FINISHED(4,"已完成"),//用户主动点击、快递员点击
    CANCELED(5,"已取消"),//用户点击取消、延迟支付时间截止（延迟消息队列）

    //以下两种在“支付后扣除库存”的情形下发生。预扣除库存不存在以下两种情况
    PAID_BUT_STOKE_OUT(21,"已支付但无货或库存不足"),
    DEAL_STOKE_OUT(22,"已处理缺货但已支付");

    private Integer value;
    private String description;

    OrderStatus(Integer value, String description){
        this.value = value;
        this.description = description;
    }

    public Integer getValue(){
        return this.value;
    }

    public static OrderStatus toType(int value){
        return Stream.of(OrderStatus.values())
                .filter(c -> c.value==value)
                .findAny()
                .orElse(null);
    }
}
