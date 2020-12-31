package com.mybatis;

import com.jdy.mybatis2020.bean.OrdersCustom;
import com.jdy.mybatis2020.bean.QueryVo;
import com.jdy.mybatis2020.bean.User;
import com.jdy.mybatis2020.dao.impl.UserDaoImpl;
import com.jdy.mybatis2020.dao.intf.UserDao;
import com.jdy.mybatis2020.dao.intf.UserMapperDao;
import com.jdy.mybatis2020.dao.intf.UserQuery;
import com.jdy.mybatis2020.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class Test_00 {

    public static final String RESOURCE = "mybatis-config/mybatis-config_01.xml";

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void createFactory() {
        sqlSessionFactory = SqlSessionFactoryUtil.createFactory(RESOURCE);
    }

    /*****************************************使用Mybatis进行crud*****************************************************/
    /**
     * 查询数据
     */
    @Test
    public void test_Method00() {
        // 数据库会话实例,一个SqlSession对象代表和数据库的一次会话
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 查询单个记录，根据用户id查询用户信息
            User user = sqlSession.selectOne("user.findUserById", 3);
            // 模糊查询
            List<User> users = sqlSession.selectList("user.findUserLikeUsername", "小");
            // 输出用户信息
            System.out.println(user);
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 添加数据
     */
    @Test
    public void test_Method01() {
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 添加用户信息
            User user = new User();
            user.setUsername("ws");
            user.setAddress("陕西西安");
            user.setSex("1");
            user.setBirthday(new Date());
            sqlSession.insert("user.insertUser", user);
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 删除数据
     */
    @Test
    public void test_Method02() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 删除用户
            sqlSession.delete("user.deleteUserById", 27);
            // 提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 修改数据
     */

    @Test
    public void test_Method03() {
        // 数据库会话实例
        SqlSession sqlSession = null;
        try {
            // 创建数据库会话实例sqlSession
            sqlSession = sqlSessionFactory.openSession();
            // 添加用户信息
            User user = new User();
            user.setId("3");
            user.setUsername("jdy");
            user.setAddress("sxxn");
            user.setSex("1");
            user.setBirthday(new Date());
            sqlSession.update("user.updateUser", user);
            // 提交事务
            sqlSession.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /****************************************MyBatis接口开发*******************************************************/
    /**
     * 原始dao开发
     */

    @Test
    public void test_Method04() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(3);
        System.out.println("user = " + user);

    }

    /**
     * Mapper代理开发
     */

    @Test
    public void test_Method05() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
            User user = mapper.findUserById("3");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**************************************参数传递*************************************************/
    /**
     * 传递pojo包装对象
     */
    @Test
    public void test_Method06() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
            User user1 = new User();
            user1.setId("3");
            user1.setUsername("jdy");
            QueryVo queryVo = new QueryVo();
            queryVo.setUser(user1);
            User user = mapper.findUserByQueryVo(queryVo);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 命名参数
     *
     * @Param起一个名字
     */

    @Test
    public void test_Method07() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            UserQuery mapper = sqlSession.getMapper(UserQuery.class);
            User user = mapper.queryUser(3);
            System.out.println("user = " + user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 传递Map
     */
    @Test
    public void test_Method08() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
            Map<String, Object> maps = new HashMap<>();
            maps.put("id", 3);
            User user = mapper.findUserByMap(maps);
            System.out.println("user = " + user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 传递List
     */
    @Test
    public void test_Method09() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
            List<User> userlist = new ArrayList<User>();
            User user = new User();
            user.setId("3");
            userlist.add(user);
            user = new User();
            user.setId("3");
            userlist.add(user);
            List<User> users  = mapper.selectUserByList(userlist);
            System.out.println("user = " + users);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /************************************级联查询*********************************************/

    /**
     * 一对一查询
     */

    @Test
    public void test_Method010() {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            UserMapperDao mapper = sqlSession.getMapper(UserMapperDao.class);
            List<OrdersCustom> ordersCustom = mapper.findOrdersList(3);
            System.out.println("ordersCustom = " + ordersCustom);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
