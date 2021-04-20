package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class AccessToken {
    @Id
    @GeneratedValue
    private int id;
    @Column(updatable = false)
    private String accessToken;
    @Column(updatable = false)
    @CreationTimestamp
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
