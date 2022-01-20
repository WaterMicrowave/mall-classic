package com.hlkj.mallclassic.bo;

import java.io.Serializable;

/**
 * @program: mall-classic
 * @description: 处理库存归还，对应order表的snap_items
 * @author: 李向平
 * @create: 2021-04-03 14:56
 */
public class OrderBO implements Serializable {
    private String id;//sku或spu 的id
    private Integer count;
    private Boolean hasSku;

    @Override
    public String toString() {
        return "OrderBO{" +
                "spuOrSkuId='" + id + '\'' +
                ", count=" + count +
                ", hasSku=" + hasSku +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getHasSku() {
        return hasSku;
    }

    public void setHasSku(Boolean hasSku) {
        this.hasSku = hasSku;
    }
}
