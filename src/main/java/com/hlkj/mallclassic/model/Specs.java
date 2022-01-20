package com.hlkj.mallclassic.model;

import java.io.Serializable;

/**
 * @ProjectName: mall-classic
 * @Package: com.hlkj.mallclassic.model
 * @ClassName: Specs
 * @Author: Administrator
 * @Description: 业务对象：spu规格
 * @Date: 2021/3/10 11:31
 * @Version: 1.0
 */
public class Specs implements Serializable {
    private String key_id;
    private String key;
    private String value_id;
    private String value;

    @Override
    public String toString() {
        return "Specs{" +
                "key_id='" + key_id + '\'' +
                ", key='" + key + '\'' +
                ", value_id='" + value_id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
