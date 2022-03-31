package com.jalivv.spring.a13;

import java.lang.reflect.Method;

/**
 * @Description
 * @Date 2022/3/31 09:53
 * @Created by jalivv
 */
public class A13App {
    interface Foo{
        void foo();
        int bar() ;
    }

    static class Target implements Foo{
        @Override
        public void foo() {
            System.out.println("target foo");
        }

        @Override
        public int bar() {
            System.out.println("target bar");
            return 100;
        }
    }

    //interface InvocationHandler{
    //    Object invoke(Object proxy, Method method,Object[] args) throws Exception;
    //}


    public static void main(String[] args) {
        Foo proxy = new $Proxy0((p,method,arg)->{
            // 1. 功能增强
            System.out.println("before....");

            // 2. 调用目标
            return method.invoke(new Target(), arg);
        });
            proxy.foo();
            proxy.bar();
    }
}
