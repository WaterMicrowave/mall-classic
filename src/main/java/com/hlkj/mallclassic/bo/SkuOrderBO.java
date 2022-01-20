package com.hlkj.mallclassic.bo;

import com.hlkj.mallclassic.dto.SkuInfoDTO;
import com.hlkj.mallclassic.model.Sku;
import com.hlkj.mallclassic.model.Spu;

import java.math.BigDecimal;

/**
 * @program: mall-classic
 * @description: 业务对象：处理复杂业务逻辑时的参数传递
 * @author: 李向平
 * @create: 2021-03-21 16:37
 */
public class SkuOrderBO {
    private BigDecimal actualPrice;//单个商品总价
    private Integer count;//数量
    private String categoryId;//商品所在分类

    public BigDecimal getTotalPrice(){
        return this.actualPrice.multiply(new BigDecimal(this.count));
    }

    public SkuOrderBO(Boolean hasSku, Spu spu, Sku sku, SkuInfoDTO skuInfoDTO) {
        if (hasSku){
            actualPrice = sku.getActualPrice();
            count = skuInfoDTO.getCount();
            categoryId = sku.getCategoryId();
        }else{
            actualPrice = spu.getActualPrice();
            count = skuInfoDTO.getCount();
            categoryId = spu.getCategoryId();
        }
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
