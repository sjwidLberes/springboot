package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 */
@Entity
@Data
public class Commodity {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private CommodityType type;
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
}
