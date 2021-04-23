package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 快递状态表（待收货/已完成）
 */
@Entity
@Data
public class OderPayState {
    @Id
    @GeneratedValue
    private int id;
    private String name;

}
