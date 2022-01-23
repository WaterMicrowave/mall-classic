package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_collect", schema = "wechat_mall", catalog = "")
public class Collect {
    @Id
    private String id;
    private String spuName;
    private String subtitle;
    private String spuId;
    private String skuId;//该属性暂不需要（参考淘宝）
    private String img;
    private BigDecimal snapshotPrice;//价格快照（取sku最低）
    private String userId;
    @Column(name = "create_time", insertable = false, updatable = false)
    private Timestamp createTime;

    @Transient
    private Long collectCount;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "spu_name")
    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    @Basic
    @Column(name = "subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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
    @Column(name = "sku_id")
    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
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
    @Column(name = "snapshot_price")
    public BigDecimal getSnapshotPrice() {
        return snapshotPrice;
    }

    public void setSnapshotPrice(BigDecimal snapshotPrice) {
        this.snapshotPrice = snapshotPrice;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collect that = (Collect) o;
        return userId == that.userId &&
                Objects.equals(id, that.id) &&
                Objects.equals(spuName, that.spuName) &&
                Objects.equals(subtitle, that.subtitle) &&
                Objects.equals(spuId, that.spuId) &&
                Objects.equals(skuId, that.skuId) &&
                Objects.equals(img, that.img) &&
                Objects.equals(snapshotPrice, that.snapshotPrice) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spuName, subtitle, spuId, skuId, img, snapshotPrice, userId, createTime);
    }
}
