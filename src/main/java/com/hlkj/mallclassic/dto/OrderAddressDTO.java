package com.hlkj.mallclassic.dto;

/**
 * @program: mall-classic
 * @description: 下单时前端传递的收货地址对象（对应小程序地址选择返回的数据）
 * @author: 李向平
 * @create: 2021-03-20 10:53
 */
public class OrderAddressDTO {
    private String userName;
    private String province;
    private String city;
    private String county;
    private String mobile;
    private String nationalCode;//国家码
    private String postalCode;//邮政编码
    private String detail;

    @Override
    public String toString() {
        return "OrderAddressDTO{" +
                "userName='" + userName + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
