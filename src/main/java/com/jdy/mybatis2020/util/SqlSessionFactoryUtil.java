package com.jdy.mybatis2020.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    public static SqlSessionFactory createFactory(String resource){
        try {
            // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
            InputStream inputStream = Resources.getResourceAsStream(resource);
            return  new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
