package com.jdy.mybatis2020.dao.intf;

import com.jdy.mybatis2020.bean.OrdersCustom;
import com.jdy.mybatis2020.bean.QueryVo;
import com.jdy.mybatis2020.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapperDao {

    /**
     * 根据Id获取用户信息
     * @param id
     * @return
     */
    public User findUserById(String id);

    public User findUserByQueryVo(QueryVo vo);

    public User findUserByMap(Map<String,Object> maps);

    public List<User> selectUserByList(List<User> users);

    public List<OrdersCustom> findOrdersList(Integer id);
}
