package com.hlkj.mallclassic.vo;

import com.hlkj.mallclassic.model.Specs;

import java.util.Arrays;
import java.util.List;

public class SkuVO {
    private String id;
    private String title;
    private String price;
    private String discount_price;
    private String img;
    private String spu_id;
    private String categoryId;
    private List<Specs> specs;
    private String code;//15$1-18#6-28",
    private byte online;
    private Integer stock;

    @Override
    public String toString() {
        return "SkuVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", discount_price='" + discount_price + '\'' +
                ", img='" + img + '\'' +
                ", spu_id='" + spu_id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", specs=" + specs +
                ", code='" + code + '\'' +
                ", online=" + online +
                ", stock=" + stock +
                '}';
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public List<Specs> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Specs> specs) {
        this.specs = specs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
