package com.jalivv.spring.a15;

import com.jalivv.spring.a14.Target;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Description
 * @Date 2022/4/3 09:04
 * @Created by jalivv
 */
public class A15App {



    interface Inter{
        void foo();
        void bar();
    }

    static class Target1 implements Inter{
        @Override
        public void foo() {
            System.out.println("target1...foo()");
        }

        @Override
        public void bar() {
            System.out.println("target1...bar()");
        }
    }

    static class Target2 implements Inter {
        @Override
        public void foo() {
            System.out.println("target2...foo()");
        }

        @Override
        public void bar() {
            System.out.println("target2...bar()");
        }
    }

    public static void main(String[] args) {

        // 1. 备好切点
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* foo())");

        // 2. 备好通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before");
                Object ans = invocation.proceed();
                System.out.println("after");
                return ans;
            }

        };
        // 3. 备好切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, advice);
        // 4. 创建代理
        Target1 t1 = new Target1();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(t1);
        factory.addAdvisor(advisor);
        Inter proxy = (Inter) factory.getProxy();
        proxy.foo();
        proxy.bar();

    }
}
