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
            // return method.invoke(target, objects);
            return method.invoke(target, objects);
        });

        p.save();
        p.save(1);
        p.save(2L);
    }
}
