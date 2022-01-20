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
public enum CouponStatus {
    AVAILABLE(1,"可以使用，未过期"),
    USED(2,"已使用"),
    EXPIRED(3,"未使用，已过期");

    private Integer value;
    private String description;

    CouponStatus(Integer value, String description){
        this.value = value;
        this.description = description;
    }

    public Integer getValue(){
        return this.value;
    }

    public static CouponStatus toType(int value){
        return Stream.of(CouponStatus.values())
                .filter(c -> c.value==value)
                .findAny()
                .orElse(null);
    }
}
