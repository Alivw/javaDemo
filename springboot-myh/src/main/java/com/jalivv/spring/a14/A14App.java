package com.jalivv.spring.a14;

/**
 * @Description
 * @Date 2022/3/31 13:28
 * @Created by jalivv
 */
public class A14App {

    public static void main(String[] args) {
        Target target = new Target();
        Proxy p = new Proxy((o, method, objects, methodProxy) -> {
            System.out.println("before....");
            // return method.invoke(target, objects);   // 内部反射 结合目标使用
            // return methodProxy.invoke(target, objects);  // 内部无反射，结合目标使用
            return methodProxy.invokeSuper(o, objects);     // 内部无反射，结合代理使用
        });

        p.save();
        p.save(1);
        p.save(2L);
    }
}
