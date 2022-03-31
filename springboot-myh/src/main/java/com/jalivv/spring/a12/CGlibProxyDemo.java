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
        public void  foo1(){
            System.out.println("foo1");
        }
    }

    public static void main(String[] args) {
        // 代理是子类型 目标是父类型
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
                if (method.getName().equals("foo1")) {
                    System.out.println("before");
                }else
                    System.out.println("before else");

                Object invoke = method.invoke(target, objects); // 用方法反射调用目标
                invoke = methodProxy.invoke(target, objects);   // 内部没有用反射，需要目标 spring 使用的
                invoke = methodProxy.invokeSuper(o, objects);   // 内部没有用反射，需要代理
                System.out.println("after");
                return invoke;
            }
        });

        target.foo1();

    }
}
