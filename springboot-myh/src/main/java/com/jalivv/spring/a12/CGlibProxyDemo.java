package com.jalivv.spring.a12;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Date 2022/3/30 17:25
 * @Created by jalivv
 */
public class CGlibProxyDemo {

    static  class  Target{
        public void foo(){
            System.out.println("foo");
        }
    }

    public static void main(String[] args) {
        Target target = (Target) Enhancer.create(Target.class, new MethodInterceptor() {
            Target target = new Target();

            /**
             * @param o           代理对象自己
             * @param method      代理类中执行的方法
             * @param objects     方法参数
             * @param methodProxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                Object invoke = method.invoke(target, objects);
                System.out.println("after");
                return invoke;
            }
        });

        target.foo();

    }
}
