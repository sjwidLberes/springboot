package com.example.demo.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 微商认证表
 */
@Entity
@Data
public class Microboss {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "openId", referencedColumnName = "openId", nullable = false)
    private User user;
    @Column(nullable = false)
    private String team;
    @Column(nullable = false)
    private String certificateUrl;
    @ManyToOne
    @JoinColumn(name = "state",  nullable = false)
    private MicrobossState state;
    private String stateDesc;
    @UpdateTimestamp
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm")
    private Date timestamp;

}
