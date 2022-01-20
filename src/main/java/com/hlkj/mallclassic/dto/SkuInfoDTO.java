package com.hlkj.mallclassic.dto;

/**
 * @program: mall-classic
 * @description: 下单时传递的商品对象
 * @author: 李向平
 * @create: 2021-03-20 10:52
 */
public class SkuInfoDTO {
    /**
     * 考虑到前端传递信息不可信，只需传递基本信息数据即可。由后端进行查询确定
     */
    private String id;
    private Integer count;
    private Boolean hasSku;//是否是多规格商品

    @Override
    public String toString() {
        return "SkuInfoDTO{" +
                "id='" + id + '\'' +
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
