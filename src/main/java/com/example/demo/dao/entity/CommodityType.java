package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 商品类型表
 */
@Entity
@Data
public class CommodityType {
    @Id
    @GeneratedValue
    private int id;
    private String name;

}
