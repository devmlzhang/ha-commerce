package com.ha.file.pojo;

import javax.persistence.*;

@Table(name = "pub_address")
public class PubAddress {
    /**
     * UUID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 省ID
     */
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 市ID
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 县ID
     */
    @Column(name = "district_id")
    private String districtId;

    /**
     * 街道名称
     */
    private String street;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 关联的业务ID
     */
    @Column(name = "biz_id")
    private String bizId;

    /**
     * 业务类型  1：注册地址;2：其他地址
     */
    @Column(name = "biz_type")
    private Integer bizType;

    /**
     * 获取UUID
     *
     * @return id - UUID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置UUID
     *
     * @param id UUID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取省ID
     *
     * @return province_id - 省ID
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省ID
     *
     * @param provinceId 省ID
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取市ID
     *
     * @return city_id - 市ID
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置市ID
     *
     * @param cityId 市ID
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取县ID
     *
     * @return district_id - 县ID
     */
    public String getDistrictId() {
        return districtId;
    }

    /**
     * 设置县ID
     *
     * @param districtId 县ID
     */
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    /**
     * 获取街道名称
     *
     * @return street - 街道名称
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道名称
     *
     * @param street 街道名称
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取关联的业务ID
     *
     * @return biz_id - 关联的业务ID
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 设置关联的业务ID
     *
     * @param bizId 关联的业务ID
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * 获取业务类型  1：注册地址;2：其他地址
     *
     * @return biz_type - 业务类型  1：注册地址;2：其他地址
     */
    public Integer getBizType() {
        return bizType;
    }

    /**
     * 设置业务类型  1：注册地址;2：其他地址
     *
     * @param bizType 业务类型  1：注册地址;2：其他地址
     */
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
}