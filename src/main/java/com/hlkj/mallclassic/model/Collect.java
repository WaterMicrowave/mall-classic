package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "collect", schema = "wechat_mall", catalog = "")
public class Collect {
    @Id
    private String id;
    private String spuId;
    private String skuId;
    private BigDecimal snapshotPrice;
    private String userId;
    @Column(name = "create_time", insertable = false, updatable = false)
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collect that = (Collect) o;
        return userId == that.userId &&
                Objects.equals(id, that.id) &&
                Objects.equals(spuId, that.spuId) &&
                Objects.equals(skuId, that.skuId) &&
                Objects.equals(snapshotPrice, that.snapshotPrice) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spuId, skuId, snapshotPrice, userId, createTime);
    }
}
