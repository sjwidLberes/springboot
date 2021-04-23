package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 验证码表（手机/邮箱 身份验证）
 */
@Entity
@Data
public class ValidationCode {
    @Id
    private String validationKey;
    @Column(nullable = false)
    private String validationCode;
    @Column(nullable = false)
    private String purpose;
    @Column(updatable = false)
    @CreationTimestamp
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date timestamp;

}
