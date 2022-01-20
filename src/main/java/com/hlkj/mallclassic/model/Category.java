package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: Category
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
public class Category {
    @Id
    private String id;
    private String name;
    //    @Basic
//    @Column(name = "description")
    private String description;
    private Integer isRoot;
    private String parentId;
    private String img;
    private String orderIndex;
    private String online;
    private String level;
    private Integer isHomeShow;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isRoot=" + isRoot +
                ", parentId='" + parentId + '\'' +
                ", img='" + img + '\'' +
                ", orderIndex='" + orderIndex + '\'' +
                ", online='" + online + '\'' +
                ", level='" + level + '\'' +
                ", isHomeShow=" + isHomeShow +
                '}';
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

    public Integer getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Integer isRoot) {
        this.isRoot = isRoot;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getIsHomeShow() {
        return isHomeShow;
    }

    public void setIsHomeShow(Integer isHomeShow) {
        this.isHomeShow = isHomeShow;
    }
}
