package com.hlkj.mallclassic.enumerate;

import java.util.stream.Stream;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.enumerate
 * @ClassName: CouponType
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 17:19
 * @Version: 1.0
 */
public enum CouponType {
    FULL_MINUS(1,"满减券"),
    FULL_OFF(2,"满减折扣券"),
    NO_THRESHOLD_MINUS(3,"无门槛减除券");//一般是小额券

    private Integer value;
    private String description;

    CouponType(Integer value, String description){
        this.value = value;
        this.description = description;
    }

    public Integer getValue(){
        return this.value;
    }

    public static CouponType toType(int value){
        return Stream.of(CouponType.values())
                .filter(c -> c.value==value)
                .findAny()
                .orElse(null);
    }
}
