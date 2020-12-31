package com.jdy.mybatis2020.dao.intf;

import com.jdy.mybatis2020.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserQuery {

    @Select("select * from user where id = #{id}")
    public User queryUser(@Param("id") Integer id);



}
