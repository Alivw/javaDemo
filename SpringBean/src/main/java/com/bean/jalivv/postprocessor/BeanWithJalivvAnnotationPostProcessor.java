package com.bean.jalivv.postprocessor;

import com.bean.jalivv.annotation.Jalivv;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/12 20:21
 */
@Component
public class BeanWithJalivvAnnotationPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) {
        if ("student".equals(s)) {
            Class<?> clazz = o.getClass();
            try {
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Jalivv.class)) {
                        field.setAccessible(true);
                        String value = field.getAnnotation(Jalivv.class).value();
                        field.set(o, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


            // AOP
            Object o1 = Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("前置代理逻辑");
                    method.invoke(o, args);
                    System.out.println("后置代理逻辑");
                    return null;
                }
            });
            return o1;
        }
        return o;
    }
}
