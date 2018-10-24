package com.liqiang.gradle.gradleboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class CusCustomerInfo {
    /** 序号*/
    private Long cid;

    /** 代理商编号*/
    private Long agentId;

    /** 用户编号*/
    private Long customerId;

    /** 用户账号*/
    private String customeraccount;

    /** 用户密码*/
    private String password;

    /** 投注密码*/
    private String betpassword;

    /** 用户名称*/
    private String customername;

    /** 身份证号*/
    private String idcard;

    /** 电话*/
    private String phone;

    /** 电子邮箱*/
    private String email;

    /** 状态  0锁定 1正常*/
    private Integer status;

    /** 创建时间*/
    private Date createdate;

    /** 用户性质*/
    private String customerquality;

    /** 用户等级*/
    private String customergrade;

    /** 用户战绩*/
    private String customerrecord;

    /** 用户性别  0女 1男*/
    private String customersex;

    /** 微信号*/
    private String wechatId;

    /** 居住城市*/
    private String livecity;

    /** QQ号*/
    private Integer qqId;

    /** 邮编*/
    private Integer zipcode;

    /** 头像*/
    private String headportrait;

}