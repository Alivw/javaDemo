package org.apache.ibatis;

import com.bean.jalivv.mapper.UserMapper;
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

    @Autowired
    public MyFactoryBean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }

    @Override
    public Object getObject() throws Exception {
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                //method.invoke(proxy, args);
                return null;
            }
        });
        return proxy;
    }
}
