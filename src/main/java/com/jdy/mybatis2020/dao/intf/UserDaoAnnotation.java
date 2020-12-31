package com.jdy.mybatis2020.dao.intf;

import com.jdy.mybatis2020.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserDaoAnnotation {

    /**
     * 根据Id获取用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id =#{id}")
    public User queryUserById(Integer id);


}
