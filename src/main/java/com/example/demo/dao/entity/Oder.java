package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单表
 */
@Entity
public class Oder {
    @Id
    private String id;
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
    @Column(nullable = false)
//    private int payState = 1;
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date payTime;
    private boolean allAccepted;

    @ManyToOne
    @JoinColumn(name = "commodityId", nullable = false)
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "openId", referencedColumnName = "openId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "payState", nullable = false)
    private OderPayState payState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAcceptedQuantity() {
        return acceptedQuantity;
    }

    public void setAcceptedQuantity(int acceptedQuantity) {
        this.acceptedQuantity = acceptedQuantity;
    }

    public OderPayState getPayState() {
        return payState;
    }

    public void setPayState(OderPayState payState) {
        this.payState = payState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public boolean isAllAccepted() {
        return allAccepted;
    }

    public void setAllAccepted(boolean allAccepted) {
        this.allAccepted = allAccepted;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
