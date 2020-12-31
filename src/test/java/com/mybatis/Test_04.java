package com.mybatis;

import com.jdy.mybatis2020.bean.User;
import com.jdy.mybatis2020.dao.intf.UserMapperDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test_04 {

    public static final String RESOURCE = "applicationContext.xml";
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createFactory() {
        sqlSessionFactory = (SqlSessionFactory)  new ClassPathXmlApplicationContext(RESOURCE).getBean("sqlSessionFactory");
    }



    @Test
    public void test_Method01() {

    }





}
