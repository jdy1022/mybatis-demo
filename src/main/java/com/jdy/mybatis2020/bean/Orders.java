package com.jdy.mybatis2020.bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 订单信息
 */
@Data
@ToString
public class Orders {

    private Integer id;
    private String number;
    private Date createtime;
    private String note;
    private User user;
    private List<OrderDetail> orderDetails;
}
