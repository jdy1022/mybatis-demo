package com.jdy.mybatis2020.bean;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 商品信息
 */
@Alias("itms")
@Data
@ToString
public class Items {

    private Integer id;
    private String name;
    private Float price;
    private String pic;
    private Date createtime;
    private String detail;
}
