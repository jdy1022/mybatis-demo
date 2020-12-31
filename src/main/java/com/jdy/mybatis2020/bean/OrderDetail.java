package com.jdy.mybatis2020.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 订单明细
 */
@Data
@ToString
public class OrderDetail {
    private Integer id;
    private Orders order;
    private Items items;
}
