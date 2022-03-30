package com.jalivv.spring.a12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Date 2022/3/30 15:52
 * @Created by jalivv
 */
public class JdkProxyDemo {

    interface Foo{
        void foo();
    }

    static class Target  implements  Foo{
        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }

    // jdk 只能针对接口代理
    // cglib  没有限制
    public static void main(String[] arg) {
        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();  // 用来加载在运行期间动态生成的字节码
        /**
         * public static Object newProxyInstance(ClassLoader loader,    类加载器
         *                                           Class<?>[] interfaces, 实现的接口数组
         *                                           InvocationHandler h)
         *
         * public Object invoke(Object proxy, 代理对象
         *                      Method method, 正在执行的方法对象
         *                      Object[] args) 方法的参数
         *
         */
        Target target = new Target();
        Foo before = ((Foo) Proxy.newProxyInstance(classLoader, new Class[]{Foo.class}, (proxy, method, args) -> {
            System.out.println("before");
            // 反射调用方法
            return method.invoke(target, args);
        }));


        before.foo();

    }
}
