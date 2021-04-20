package com.example.demo.dao.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 收货地址表
 */
@Entity
public class Address {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String openId;
    @Column(nullable = false)
    private String consignee;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String provincial;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String detail;
    @UpdateTimestamp
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm")
    private Date timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvincial() {
        return provincial;
    }

    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static Address parse(JSONObject data){
        Address address = new Address();
        address.setOpenId(data.getString("openId"));
        address.setConsignee(data.getString("consignee"));
        address.setPhone(data.getString("phone"));
        address.setProvincial(data.getString("provincial"));
        address.setCity(data.getString("city"));
        address.setDistrict(data.getString("district"));
        address.setDetail(data.getString("detail"));
        address.setTimestamp(new Date());
        return address;
    }
}
