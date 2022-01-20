package com.hlkj.mallclassic.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-03-22 17:35
 */
@Entity
public class Swiper {
    @Id
    private String id;
    private String title;
    private String img;
    private Byte order;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Swiper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", order=" + order +
                '}';
    }
}
