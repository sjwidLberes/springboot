package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 我领到的福利 表
 */
@Entity
public class Welfare {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "oderId", nullable = false)
    private Oder oder;
    @ManyToOne
    @JoinColumn(name = "openId", referencedColumnName = "openId", nullable = false)
    private User user;
    @Column(nullable = false)
    private String expressConsignee;
    @Column(nullable = false)
    private String expressPhone;
    @Column(nullable = false)
    private String expressAddressProvincial;
    @Column(nullable = false)
    private String expressAddressCity;
    @Column(nullable = false)
    private String expressAddressDistrict;
    @Column(nullable = false)
    private String expressAddressDetail;
    @Column(nullable = false)
    private String expressUndertakeCompany;
    @Column(nullable = false)
    private String expressNumber;
    @Column(nullable = false)
    private int quantity = 0;
    @ManyToOne
    @JoinColumn(name = "state", nullable = false)
    private WelfareState state;
    private String stateDesc;
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

    public Oder getOder() {
        return oder;
    }

    public void setOder(Oder oder) {
        this.oder = oder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExpressConsignee() {
        return expressConsignee;
    }

    public void setExpressConsignee(String expressConsignee) {
        this.expressConsignee = expressConsignee;
    }

    public String getExpressPhone() {
        return expressPhone;
    }

    public void setExpressPhone(String expressPhone) {
        this.expressPhone = expressPhone;
    }

    public String getExpressAddressProvincial() {
        return expressAddressProvincial;
    }

    public void setExpressAddressProvincial(String expressAddressProvincial) {
        this.expressAddressProvincial = expressAddressProvincial;
    }

    public String getExpressAddressCity() {
        return expressAddressCity;
    }

    public void setExpressAddressCity(String expressAddressCity) {
        this.expressAddressCity = expressAddressCity;
    }

    public String getExpressAddressDistrict() {
        return expressAddressDistrict;
    }

    public void setExpressAddressDistrict(String expressAddressDistrict) {
        this.expressAddressDistrict = expressAddressDistrict;
    }

    public String getExpressAddressDetail() {
        return expressAddressDetail;
    }

    public void setExpressAddressDetail(String expressAddressDetail) {
        this.expressAddressDetail = expressAddressDetail;
    }

    public String getExpressUndertakeCompany() {
        return expressUndertakeCompany;
    }

    public void setExpressUndertakeCompany(String expressUndertakeCompany) {
        this.expressUndertakeCompany = expressUndertakeCompany;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public WelfareState getState() {
        return state;
    }

    public void setState(WelfareState state) {
        this.state = state;
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }

}
