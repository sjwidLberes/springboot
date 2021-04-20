package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 */
@Entity
public class Commodity {
    @Id
    @GeneratedValue
    private int id;
//    private int type;
    private String name;
    private String thumbImageUrl;
    private String detailImageUrl;
    private String introduceImageUrl;
    private String shareImageUrl;
    private BigDecimal price;
    private boolean onPromotional = true;
    private BigDecimal promotionalPrice;
    private int saleQuantity = 0;
    private int minimumPurchaseQuantity = 0;
    private boolean onShelf = false;
    @UpdateTimestamp
    @JSONField(serialize = false)//, format = "yyyy-MM-dd HH:mm")
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private CommodityType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getType() {
//        return type;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }


    public CommodityType getType() {
        return type;
    }

    public void setType(CommodityType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public String getDetailImageUrl() {
        return detailImageUrl;
    }

    public void setDetailImageUrl(String detailImageUrl) {
        this.detailImageUrl = detailImageUrl;
    }

    public String getIntroduceImageUrl() {
        return introduceImageUrl;
    }

    public void setIntroduceImageUrl(String introduceImageUrl) {
        this.introduceImageUrl = introduceImageUrl;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isOnPromotional() {
        return onPromotional;
    }

    public void setOnPromotional(boolean onPromotional) {
        this.onPromotional = onPromotional;
    }

    public BigDecimal getPromotionalPrice() {
        return promotionalPrice;
    }

    public void setPromotionalPrice(BigDecimal promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public int getMinimumPurchaseQuantity() {
        return minimumPurchaseQuantity;
    }

    public void setMinimumPurchaseQuantity(int minimumPurchaseQuantity) {
        this.minimumPurchaseQuantity = minimumPurchaseQuantity;
    }

    public boolean isOnShelf() {
        return onShelf;
    }

    public void setOnShelf(boolean onShelf) {
        this.onShelf = onShelf;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
