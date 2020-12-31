package com.jdy.mybatis2020.bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class OrdersCustom {
    private String username;// 用户名称
    private String address;// 用户地
    private Integer id;
    private String number;
    private Date createtime;
    private String note;
    private User user;

}
