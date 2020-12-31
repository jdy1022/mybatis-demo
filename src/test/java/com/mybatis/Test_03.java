package com.mybatis;

import com.alibaba.fastjson.JSON;
import com.jdy.mybatis2020.bean.User;
import com.jdy.mybatis2020.dao.intf.SelectMapperDao;
import com.jdy.mybatis2020.dao.intf.UserMapperDao;
import com.jdy.mybatis2020.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class Test_03 {

    public static final String RESOURCE = "mybatis-config/mybatis-config_01.xml";

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createFactory() {
        sqlSessionFactory = SqlSessionFactoryUtil.createFactory(RESOURCE);
    }

    @Test
    public void test_Method00() {
        //获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获限mapper接口实例
        UserMapperDao dao = sqlSession.getMapper(UserMapperDao.class);
        //第一次查询
        User user = dao.findUserById("1");
        System.out.println("user = " + user);
        //第二次查询，由于是同一个session则不再向数据发出语句直接从缓存取出
        User user1 = dao.findUserById("1");
        System.out.println("user = " + user1);
        //关闭session
        sqlSession.close();
    }

}
