package com.jdy.mybatis2020.dao.intf;


import java.util.List;
import java.util.Map;

public interface SelectMapperDao {

    public List<Map<String,Object>> findOrdersMap(String id);

    public List<Map<String,Object>> findOrdersList3(String id);
}
