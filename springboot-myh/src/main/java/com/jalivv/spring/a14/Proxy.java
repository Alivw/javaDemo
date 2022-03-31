package com.jalivv.spring.a14;

import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Description
 * @Date 2022/3/31 13:30
 * @Created by jalivv
 */
public class Proxy extends Target {

   private MethodInterceptor methodInterceptor;

    public Proxy(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    private static Method save0;
    private static Method save1;
    private static Method save2;

    static {
        try {
            save0 = Target.class.getMethod("save");
            save1 = Target.class.getMethod("save", int.class);
            save2 = Target.class.getMethod("save", long.class);
        } catch (NoSuchMethodException e) {
            throw new UndeclaredThrowableException(e);
        }

    }


    @Override
    public void save() {
        try {
            methodInterceptor.intercept(this, save0, new Object[0], null);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void save(int i) {
        try {
            methodInterceptor.intercept(this, save1, new Object[]{i}, null);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public void save(long j) {
        try {
            methodInterceptor.intercept(this, save2, new Object[]{j}, null);
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
}
