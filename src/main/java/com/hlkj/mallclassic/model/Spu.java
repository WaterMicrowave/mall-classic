package com.hlkj.mallclassic.model;

import com.hlkj.mallclassic.convert.SpecsConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: Spu
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
public class Spu {
    @Id
    private String id;
    private String title;
    private String subtitle;
    private String categoryId;
    private String rootCategoryId;
    private byte online;
    private String price;
    private String discountPrice;
    private String img;
    private String description;
    private String tags;
    private int stock;
    private String sketchSpecId;
    private String defaultSkuId;
    private Boolean recommend;

    @Override
    public String toString() {
        return "Spu{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", rootCategoryId='" + rootCategoryId + '\'' +
                ", online=" + online +
                ", price='" + price + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", stock=" + stock +
                ", sketchSpecId='" + sketchSpecId + '\'' +
                ", defaultSkuId='" + defaultSkuId + '\'' +
                ", recommend=" + recommend +
                '}';
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public BigDecimal getActualPrice(){
        return discountPrice==null? new BigDecimal(this.price):new BigDecimal(this.discountPrice);
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRootCategoryId() {
        return rootCategoryId;
    }

    public void setRootCategoryId(String rootCategoryId) {
        this.rootCategoryId = rootCategoryId;
    }

    public byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSketchSpecId() {
        return sketchSpecId;
    }

    public void setSketchSpecId(String sketchSpecId) {
        this.sketchSpecId = sketchSpecId;
    }

    public String getDefaultSkuId() {
        return defaultSkuId;
    }

    public void setDefaultSkuId(String defaultSkuId) {
        this.defaultSkuId = defaultSkuId;
    }
}
