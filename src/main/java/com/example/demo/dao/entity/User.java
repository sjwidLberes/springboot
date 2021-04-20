package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue
    @JSONField(serialize = false)
    private int id;
    @Column(nullable = false)
    private String openId;
    @Column(nullable = false)
    private String nickname;
    private int sex;
    @JSONField(serialize = false)
    private String country;
    @JSONField(serialize = false)
    private String province;
    @JSONField(serialize = false)
    private String city;
    @Column(nullable = false)
    private String headImgUrl;
    @JSONField(serialize = false)
    private String privilegeList;
    @UpdateTimestamp
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm")
    private Date timestamp;
    private String phone;
    @JSONField(serialize = false)
    private int score = 0;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(String privilegeList) {
        this.privilegeList = privilegeList;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
