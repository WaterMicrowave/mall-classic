package com.hlkj.mallclassic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: Banner
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 14:39
 * @Version: 1.0
 */
@Entity
public class Banner {
    @Id
    private String id;
    private String name;
    private String description;
    private int indexSort;
    private String img;
    private Integer online;
    private String extend;

    @OneToMany(mappedBy = "banner", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<BannerItem> items;

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", indexSort=" + indexSort +
                ", img='" + img + '\'' +
                ", online=" + online +
                ", extend='" + extend + '\'' +
                ", items=" + items +
                '}';
    }

    public List<BannerItem> getItems() {
        return items;
    }

    public void setItems(List<BannerItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIndexSort() {
        return indexSort;
    }

    public void setIndexSort(int indexSort) {
        this.indexSort = indexSort;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banner banner = (Banner) o;
        return indexSort == banner.indexSort &&
                Objects.equals(id, banner.id) &&
                Objects.equals(name, banner.name) &&
                Objects.equals(description, banner.description) &&
                Objects.equals(img, banner.img) &&
                Objects.equals(online, banner.online) &&
                Objects.equals(extend, banner.extend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, indexSort, img, online, extend);
    }

}
