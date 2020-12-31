package com.jdy.mybatis2020.MyPlugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * 完成插件签名：
 * 告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class FirstPlugin implements Interceptor {

    /**
     * 拦截目标方法执行
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("MyFirstPlugin...intercept:" + invocation.getMethod());
        //动态的改变一下sql运行的参数：以前1号员工，实际从数据库查询3号员工
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        //拿到：StatementHandler==>ParameterHandler===>parameterObject
        //拿到target的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是：" + value);
        //修改完sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 11);
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }

    /**
     * 生成动态代理对象，可以使用MyBatis提 供的Plugin类的wrap方法
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        // TODO Auto-generated method stub
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象" + target);
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }

    /**
     * 注入插件配置时设置的属性
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        System.out.println("插件配置的信息：" + properties);
    }
}
