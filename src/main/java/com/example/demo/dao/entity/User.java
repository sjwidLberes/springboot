package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
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


}
