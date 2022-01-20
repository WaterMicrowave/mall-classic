package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: SkuSpec
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
@Table(name = "sku_spec", schema = "hengshi-wechat", catalog = "")
public class SkuSpec {
    private String id;
    private String spuId;
    private String skuId;
    private String keyId;
    private String valueId;

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
    @Column(name = "key_id")
    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    @Basic
    @Column(name = "value_id")
    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkuSpec skuSpec = (SkuSpec) o;
        return Objects.equals(id, skuSpec.id) &&
                Objects.equals(spuId, skuSpec.spuId) &&
                Objects.equals(skuId, skuSpec.skuId) &&
                Objects.equals(keyId, skuSpec.keyId) &&
                Objects.equals(valueId, skuSpec.valueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, spuId, skuId, keyId, valueId);
    }
}
