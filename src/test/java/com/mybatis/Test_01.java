package com.mybatis;

import com.jdy.mybatis2020.bean.User;
import com.jdy.mybatis2020.dao.impl.UserDaoImpl;
import com.jdy.mybatis2020.dao.intf.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Test_01 {

    //会话工厂
    private SqlSessionFactory sqlSessionFactory;

    {
        // 配置文件
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void test_Method00() {
        UserDao dao = new UserDaoImpl(sqlSessionFactory);
        User user = dao.queryUserById(26);
        System.out.println("user = " + user);
        User user1 = dao.queryUserById(26);
        System.out.println("user = " + user1);
    }


    @Test
    public void test_Method02() {
        UserDao dao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setUsername("ws");
        user.setAddress("陕西西安");
        user.setSex("1");
        user.setBirthday(new Date());
        boolean result = dao.insertUser(user);
        System.out.println("result = " + result);
    }

    @Test
    public void test_Method03() {
        UserDao dao = new UserDaoImpl(sqlSessionFactory);
        User user = new User();
        user.setSex("0");
        user.setId(28);
        boolean result = dao.updateUser(user);
        System.out.println("result = " + result);
    }

    @Test
    public void test_Method04() {
        UserDao dao = new UserDaoImpl(sqlSessionFactory);
        boolean result = dao.deleteUser(28);
        System.out.println("result = " + result);
    }


}
