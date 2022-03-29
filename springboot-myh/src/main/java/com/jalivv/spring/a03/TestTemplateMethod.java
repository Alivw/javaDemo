package com.jalivv.spring.a03;

import java.util.ArrayList;
import java.util.List;

public class TestTemplateMethod {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor((o) -> {
            System.out.println("解析 @Autowired ,执行依赖注入");
        });
        beanFactory.getBean();
    }


    static class MyBeanFactory {

        List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造：" + bean);
            System.out.println("依赖注入 " + bean);
            for (BeanPostProcessor p : beanPostProcessors) {
                p.inject(bean);
            }

            System.out.println("初始化 " + bean);
            return bean;
        }

        public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
            beanPostProcessors.add(postProcessor);
        }
    }

    interface BeanPostProcessor {
        void inject(Object o);   // 依赖注入 解析 @Autowired @Resource
    }
}
