package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 1. 微商身份认证 状态
 * 2.
 * 3.
 */
@Entity
@Data
public class MicrobossState {
    @Id
    @GeneratedValue
    private int id;
    private String name;

}
