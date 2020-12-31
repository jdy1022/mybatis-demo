package com.jdy.mybatis2020.dao.intf;

import java.util.List;
import java.util.Map;

public interface OneToMany {


    public List <Map<String,Object>> findOrdersDetailList();

}
