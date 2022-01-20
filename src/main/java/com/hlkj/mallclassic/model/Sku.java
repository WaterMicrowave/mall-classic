package com.hlkj.mallclassic.model;

import com.hlkj.mallclassic.convert.SpecsConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: Sku
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
public class Sku {
    @Id
    private String id;
    private String title;
    private String price;
    private String discountPrice;
    private byte online;
    private String img;
    @Convert(converter = SpecsConverter.class)
    private Specs[] specs;
    private String code;
    private int stock;
    private String spuId;
    private String categoryId;
    private String rootCategoryId;

    public BigDecimal getActualPrice(){
        return this.discountPrice==null? new BigDecimal(this.price):new BigDecimal(this.discountPrice);
    }

    @Override
    public String toString() {
        return "Sku{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                ", online=" + online +
                ", img='" + img + '\'' +
                ", specs=" + Arrays.toString(specs) +
                ", code='" + code + '\'' +
                ", stock=" + stock +
                ", spuId='" + spuId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", rootCategoryId='" + rootCategoryId + '\'' +
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

    public byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Specs[] getSpecs() {
        return specs;
    }

    public void setSpecs(Specs[] specs) {
        this.specs = specs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
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
}
