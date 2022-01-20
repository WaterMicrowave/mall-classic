package com.hlkj.mallclassic.vo;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.vo
 * @ClassName: CategoryVO
 * @Author: Administrator
 * @Description: 所有分类id、title
 * @Date: 2021/3/10 10:24
 * @Version: 1.0
 */
public class CategorySimpleVO {
    private String id;
    private String name;
    private String img;

    @Override
    public String toString() {
        return "CategorySimpleVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}
