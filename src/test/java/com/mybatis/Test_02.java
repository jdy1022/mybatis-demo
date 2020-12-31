package com.mybatis;

import com.alibaba.fastjson.JSON;
import com.jdy.mybatis2020.dao.intf.SelectMapperDao;
import com.jdy.mybatis2020.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class Test_02 {

    public static final String RESOURCE = "mybatis-config/mybatis-config_01.xml";

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createFactory() {
        sqlSessionFactory = SqlSessionFactoryUtil.createFactory(RESOURCE);
    }

    /**
     * association
     */
    @Test
    public void test_Method00() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SelectMapperDao dao = sqlSession.getMapper(SelectMapperDao.class);
        List<Map<String, Object>> ordersMap = dao.findOrdersMap("1");
        System.out.println("ordersMap = " + JSON.toJSONString(ordersMap));
    }


    /**
     * 分段查询
     */
    @Test
    public void test_Method02() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SelectMapperDao dao = sqlSession.getMapper(SelectMapperDao.class);
        List<Map<String, Object>> ordersMap = dao.findOrdersList3("3");
        System.out.println("ordersMap = " + JSON.toJSONString(ordersMap));
    }

}
