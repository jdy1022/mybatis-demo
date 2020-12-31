package com.jdy.mybatis2020.dao.intf;

import com.jdy.mybatis2020.bean.OrdersCustom;

import java.util.List;
import java.util.Map;

public interface OneToOne {

    public List<OrdersCustom> findOrdersList();



    public List <Map<String,Object>> findOrdersMap();

}
