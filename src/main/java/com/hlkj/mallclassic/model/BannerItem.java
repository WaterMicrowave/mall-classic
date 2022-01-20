package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: mall-classic
 * @description:
 * @author:
 * @create: 2021-03-25 18:15
 */
@Entity
@Table(name = "banner_item", schema = "hengshi-wechat", catalog = "")
public class BannerItem {
    @Id
    private String id;
    private String title;
    private String img;
    private String bannerId;
    @ManyToOne
    @JoinColumn(name = "bannerId",insertable = false,updatable = false)
    private Banner banner;

    @Override
    public String toString() {
        return "BannerItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", bannerId='" + bannerId + '\'' +
                ", banner=" + banner +
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
