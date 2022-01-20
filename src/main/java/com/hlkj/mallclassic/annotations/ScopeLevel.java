package com.hlkj.mallclassic.annotations;

import java.lang.annotation.*;

/**
 * @ProjectName: hxfy
 * @Package: com.hlkj.classics.annotation
 * @ClassName: ScopeLevel
 * @Author: Administrator
 * @Description: 接口权限注解
 * @Date: 2020/12/10 13:56
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE}) //使用位置（类，方法）
@Retention(RetentionPolicy.RUNTIME) //加载到jvm里运行
@Documented
public @interface ScopeLevel {
    int value() default 4;
}