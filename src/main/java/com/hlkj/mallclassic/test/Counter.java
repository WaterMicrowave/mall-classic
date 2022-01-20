package com.hlkj.mallclassic.test;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @program: mall-classic
 * @description: 测试
 * @author:
 * @create: 2021-03-21 15:29
 */
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)//如果是接口用INTERFACES
public class Counter {
    private Integer count = 1;

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
