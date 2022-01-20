package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: SpuDetailImg
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
@Table(name = "spu_detail_img", schema = "hengshi-wechat", catalog = "")
public class SpuDetailImg {
    private String id;
    private String img;
    private String spuId;
    private String index;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "spu_id")
    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    @Basic
    @Column(name = "index")
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpuDetailImg that = (SpuDetailImg) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(img, that.img) &&
                Objects.equals(spuId, that.spuId) &&
                Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, img, spuId, index);
    }
}
