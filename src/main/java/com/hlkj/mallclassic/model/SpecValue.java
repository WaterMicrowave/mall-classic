package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: SpecValue
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
@Table(name = "spec_value", schema = "hengshi-wechat", catalog = "")
public class SpecValue {
    private String id;
    private String value;
    private String extend;
    private String specId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "extend")
    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    @Basic
    @Column(name = "spec_id")
    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecValue specValue = (SpecValue) o;
        return Objects.equals(id, specValue.id) &&
                Objects.equals(value, specValue.value) &&
                Objects.equals(extend, specValue.extend) &&
                Objects.equals(specId, specValue.specId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, extend, specId);
    }
}
