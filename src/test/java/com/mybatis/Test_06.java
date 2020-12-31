package com.mybatis;

import com.jdy.mybatis2020.bean.User;
import com.jdy.mybatis2020.dao.intf.UserMapperDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test_06 {

    private ApplicationContext ctx = null;
    private SqlSessionFactory sqlSessionFactory = null;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) ctx.getBean("sqlSessionFactory");
    }





    @Test
    public void test_Method01() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        //获限mapper接口实例
        UserMapperDao dao1 = sqlSession1.getMapper(UserMapperDao.class);
        //使用session1执行第一次查询
        User user1 = dao1.queryUserById(26);
        System.out.println("user = " + user1);
        sqlSession1.commit();
        //关闭session
        sqlSession1.close();

        //使用session2执行第二次查询，由于开启了二级缓存这里从缓存中获取数据不再向数据库发出sql
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        //获限mapper接口实例
        UserMapperDao dao2 = sqlSession2.getMapper(UserMapperDao.class);
        //第二次查询，由于是同一个session则不再向数据发出语句直接从缓存取出
        User user2 = dao2.queryUserById(26);
        System.out.println("user = " + user2);
        //关闭session
        sqlSession1.close();
    }


    @Test
    public void test_Method02() {
        //获取session
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        //获限mapper接口实例
        UserMapperDao dao1 = sqlSession1.getMapper(UserMapperDao.class);
        //使用session1执行第一次查询
        User user1 = dao1.queryUserById(26);
        System.out.println("user = " + user1);


        //使用session2执行第二次查询，由于开启了二级缓存这里从缓存中获取数据不再向数据库发出sql
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        //获限mapper接口实例
        UserMapperDao dao2 = sqlSession2.getMapper(UserMapperDao.class);
        //第二次查询，由于是同一个session则不再向数据发出语句直接从缓存取出
        User user2 = dao2.queryUserById(26);
    }

    @Test
    public void test_Method03() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapperDao dao1 = sqlSession1.getMapper(UserMapperDao.class);
        User user1 = dao1.queryUserById(26);
        System.out.println("user1 = " + user1);
        sqlSession1.commit();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapperDao dao2 = sqlSession2.getMapper(UserMapperDao.class);
        User user2 = dao2.queryUserById(26);
        System.out.println("user2 = " + user2);

        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapperDao dao3 = sqlSession3.getMapper(UserMapperDao.class);
        User user = new User();
        user.setSex("0");
        user.setId(28);
        boolean result = dao3.updateUser(user);
        System.out.println("result = " + result);
        sqlSession3.commit();


        dao2 = sqlSession2.getMapper(UserMapperDao.class);
        user2 = dao2.queryUserById(26);
        System.out.println("user2 = " + user2);

    }

    @Test
    public void test_Method04() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapperDao dao = sqlSession.getMapper(UserMapperDao.class);
        boolean result = dao.deleteUser(28);
        System.out.println("result = " + result);
    }


}
