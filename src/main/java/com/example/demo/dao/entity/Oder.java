package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单表
 */
@Entity
@Data
public class Oder {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "commodityId", nullable = false)
    private Commodity commodity;
    @ManyToOne
    @JoinColumn(name = "openId", referencedColumnName = "openId", nullable = false)
    private User user;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private String word;
    @Column(nullable = false)
    private int acceptedQuantity = 0;
    @Column(updatable = false)
    @CreationTimestamp
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @ManyToOne
    @JoinColumn(name = "payState", nullable = false)
    private OderPayState payState;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date payTime;
    private boolean allAccepted;



}
