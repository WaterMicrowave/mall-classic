package com.hlkj.mallclassic.vo;

import java.util.List;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.vo
 * @ClassName: CategoryVO
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:24
 * @Version: 1.0
 */
public class CategoryVO {
    private String id;
    private String name;
    private String description;
    private String img;
    private Integer isHomeShow;

    @Override
    public String toString() {
        return "CategoryVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getIsHomeShow() {
        return isHomeShow;
    }

    public void setIsHomeShow(Integer isHomeShow) {
        this.isHomeShow = isHomeShow;
    }
}
