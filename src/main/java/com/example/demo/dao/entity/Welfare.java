package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 我领到的福利 表
 */
@Entity
@Data
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


}
