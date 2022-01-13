package org.apache.ibatis;

import com.bean.jalivv.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 08:29
 */
public class MyFactoryBean implements FactoryBean {

    private Class mapperClass;

    private SqlSession sqlSession;

    @Autowired
    public MyFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    /**
     * set 注入
     *
     * @param sqlSessionFactory
     */
    @Autowired
    public void setSession(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addMapper(mapperClass);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }

    @Override
    public Object getObject() throws Exception {
        //Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
        //    @Override
        //    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //        System.out.println(method.getName());
        //        //method.invoke(proxy, args);
        //        return null;
        //    }
        //});
        //return proxy;

        return sqlSession.getMapper(mapperClass);
    }
}
