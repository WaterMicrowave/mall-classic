package com.hlkj.mallclassic.vo;

import com.hlkj.mallclassic.model.SpuDetailImg;
import com.hlkj.mallclassic.model.SpuImg;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.vo
 * @ClassName: SpuVO
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:25
 * @Version: 1.0
 */
public class SpuVO {
    private String id;
    private String title;
    private String subtitle;
    private String description;
    private String categoryId;
    private String price;
    private String discount_price;
    private String img;
    private String tags;
    private List<SkuVO> sku_list;
    private List<SpuImg> spu_img_list;
    private List<SpuDetailImg> spu_detail_img_list;
    private String default_sku_id;
    private Integer stock;
    private byte online;

    @Override
    public String toString() {
        return "SpuVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", price='" + price + '\'' +
                ", discount_price='" + discount_price + '\'' +
                ", img='" + img + '\'' +
                ", tags='" + tags + '\'' +
                ", sku_list=" + sku_list +
                ", spu_img_list=" + spu_img_list +
                ", spu_detail_img_list=" + spu_detail_img_list +
                ", default_sku_id='" + default_sku_id + '\'' +
                ", stock=" + stock +
                ", online=" + online +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<SkuVO> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<SkuVO> sku_list) {
        this.sku_list = sku_list;
    }

    public List<SpuImg> getSpu_img_list() {
        return spu_img_list;
    }

    public void setSpu_img_list(List<SpuImg> spu_img_list) {
        this.spu_img_list = spu_img_list;
    }

    public List<SpuDetailImg> getSpu_detail_img_list() {
        return spu_detail_img_list;
    }

    public void setSpu_detail_img_list(List<SpuDetailImg> spu_detail_img_list) {
        this.spu_detail_img_list = spu_detail_img_list;
    }

    public String getDefault_sku_id() {
        return default_sku_id;
    }

    public void setDefault_sku_id(String default_sku_id) {
        this.default_sku_id = default_sku_id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }
}
