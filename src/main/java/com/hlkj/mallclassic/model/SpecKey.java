package com.hlkj.mallclassic.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: SpecKey
 * @Author: Administrator
 * @Description:
 * @Date: 2021/3/10 10:10
 * @Version: 1.0
 */
@Entity
@Table(name = "spec_key", schema = "hengshi-wechat", catalog = "")
public class SpecKey {
    private String id;
    private String name;
    private String unit;
    private byte standard;
    private String description;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "standard")
    public byte getStandard() {
        return standard;
    }

    public void setStandard(byte standard) {
        this.standard = standard;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecKey specKey = (SpecKey) o;
        return standard == specKey.standard &&
                Objects.equals(id, specKey.id) &&
                Objects.equals(name, specKey.name) &&
                Objects.equals(unit, specKey.unit) &&
                Objects.equals(description, specKey.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit, standard, description);
    }
}
