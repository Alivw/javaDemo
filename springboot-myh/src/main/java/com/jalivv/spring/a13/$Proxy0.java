package com.jalivv.spring.a13;

import com.jalivv.spring.a13.A13App.Foo;
//import com.jalivv.spring.a13.A13App.InvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Description
 * @Date 2022/3/31 09:55
 * @Created by jalivv
 */
public class $Proxy0 extends Proxy implements Foo {

    static Method foo;
    static Method bar;
    static {
        try {
            foo = Foo.class.getMethod("foo");
            bar = Foo.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    //private InvocationHandler h;

    //public $Proxy0(InvocationHandler handler) {
    //    this.handler = handler;
    //}
    public $Proxy0(InvocationHandler handler) {
        super(handler);
    }

    @Override
    public void foo(){
        try {
            h.invoke(this,foo, new Object[0]);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    @Override
    public int bar() {
        try {
            return (int) h.invoke(this, bar, new Object[0]);
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }



}
