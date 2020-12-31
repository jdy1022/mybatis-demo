package com.jdy.mybatis2020.jdbc;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBC_Demo {
    private String userName = null;
    private String password = null;
    private String jdbcUrl = null;
    private String driverClass = null;


    private ResultSet resultSet =null;
    private PreparedStatement preparedStatement  =null;
    private Connection connection =null;

    /**
     * 获取配置文件中的数据库信息
     */
    {
        ClassLoader classLoader2 = this.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader2.getResourceAsStream("properties/jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            userName = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            jdbcUrl = properties.getProperty("db.jdbcUrl");
            driverClass = properties.getProperty("db.driverClass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void jdbcDemo () throws ClassNotFoundException {

        try {
            //第一步：加载数据库驱动
            Class.forName(driverClass);
            //第二步：通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            //第三步：定义sql语句 ?表示占位符
            String sql = "select * from items where id = ?";
            //第四步：获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //第五步：设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setInt(1, 1);
            //第六步：向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //第七步：遍历查询结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "  "
                        + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
