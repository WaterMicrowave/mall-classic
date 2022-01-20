package com.hlkj.mallclassic.vo;

/**
 * @program: mall-classic
 * @description: 购物车数据同步VO
 * @author: 李向平
 * @create: 2021-03-24 13:02
 */
public class CartDataSyncVO {
    private String id;
    private Boolean hasSku;
    private SpuVO spuVO;
    private SkuVO skuVO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getHasSku() {
        return hasSku;
    }

    public void setHasSku(Boolean hasSku) {
        this.hasSku = hasSku;
    }

    public SpuVO getSpuVO() {
        return spuVO;
    }

    public void setSpuVO(SpuVO spuVO) {
        this.spuVO = spuVO;
    }

    public SkuVO getSkuVO() {
        return skuVO;
    }

    public void setSkuVO(SkuVO skuVO) {
        this.skuVO = skuVO;
    }
}
