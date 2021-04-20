package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 微商认证表
 */
@Entity
public class Microboss {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "openId", referencedColumnName = "openId", nullable = false)
    private User user;
    @Column(nullable = false)
    private String team;
    @Column(nullable = false)
    private String certificateUrl;
    @ManyToOne
    @JoinColumn(name = "state",  nullable = false)
    private MicrobossState state;
    private String stateDesc;
    @UpdateTimestamp
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm")
    private Date timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCertificateUrl() {
        return certificateUrl;
    }

    public void setCertificateUrl(String certificateUrl) {
        this.certificateUrl = certificateUrl;
    }

    public MicrobossState getState() {
        return state;
    }

    public void setState(MicrobossState state) {
        this.state = state;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
