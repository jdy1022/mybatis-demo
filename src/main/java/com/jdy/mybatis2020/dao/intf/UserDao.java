package com.jdy.mybatis2020.dao.intf;

import com.jdy.mybatis2020.bean.User;

public interface UserDao {

    /**
     * 根据Id获取用户信息
     * @param id
     * @return
     */
    public User findUserById(Integer id);
}
