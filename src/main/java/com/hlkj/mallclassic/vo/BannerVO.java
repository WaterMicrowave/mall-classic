package com.hlkj.mallclassic.vo;


/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.vo
 * @ClassName: BannerVO
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/9 15:53
 * @Version: 1.0
 */
public class BannerVO {
    private String id;
    private String name;
    private String img;

    @Override
    public String toString() {
        return "BannerVO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BannerVO(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public BannerVO() {
    }
}
