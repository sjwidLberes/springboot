package com.example.demo.dao.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
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
@Data
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
