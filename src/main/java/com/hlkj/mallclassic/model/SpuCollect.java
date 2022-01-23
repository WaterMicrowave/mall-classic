package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "spu_collect", schema = "wechat_mall", catalog = "")
public class SpuCollect {
    @Id
    private String id;
    private String spuName;
    private String spuId;
    private String skuId;
    private long count;
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
    @Column(name = "spu_name")
    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
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
    @Column(name = "count")
    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
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
        SpuCollect that = (SpuCollect) o;
        return count == that.count &&
                Objects.equals(id, that.id) &&
                Objects.equals(spuName, that.spuName) &&
                Objects.equals(spuId, that.spuId) &&
                Objects.equals(skuId, that.skuId) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spuName, spuId, skuId, count, createTime);
    }
}
