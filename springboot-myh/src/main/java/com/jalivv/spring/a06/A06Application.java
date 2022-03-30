package com.jalivv.spring.a06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description Aware 接口及 initializingBean 接口
 * @Date 2022/3/30 10:13
 * @Created by jalivv
 */
public class A06Application {


    private static final Logger logger = LoggerFactory.getLogger(A06Application.class);

    public static void main(String[] args) {
        /**
         * 1. Aware 接口用于注入一些与容器相关的信息，例如
         *    a. BeanNameAware 注入bean 的名字
         *    b. BeanFactoryAware 注入 BeanFactory 容器
         *    c. ApplicationContextAware 注入 ApplicationContext 容器
         *    d. EmbeddedValueResolverAware ${}
         */


        GenericApplicationContext context = new GenericApplicationContext();
        //context.registerBean(MyBean.class);
        // 解析 autowired 、 postConstruct BeanPostProcessor， 不加，这俩注解是不起作用的

        context.registerBean(Config1.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        // 解析 @Bean
        context.registerBean(ConfigurationClassPostProcessor.class);


        context.refresh();
        context.close();


        /**
         *  b、c、d 的功能用 @Autowired 就能实现，为什么还要用 Aware 接口？
         *  简单的说：
         *      a. @Autowired 的解析需要用到 BeanPostProcessor，属于扩展功能
         *      b. 而 Aware 接口属于内置功能，不加任何扩展，Spring就能识别
         *  某些情况下，扩展功能会失效，而内置功能不会失效
         *  例1：会发现用 Aware 注入 ApplicationContext 成功， 而 @Autowired 注入 ApplicationContext 失败
         */


        /**
         *
         *  例2： java 配置类在添加了 BeanFactoryPostProcessor 之后，
         *      会发现用传统接口方式的注入和初始化仍然成功，而 @Autowired 和 @PostConstruct 的注入和初始化失败
         *  原因：
         *      在 refresh 过程中， 先找到所有的 BeanFactoryPostProcessor ，然后进行注册， 再将单例 bean 实例化 执行 Aware 接口，初始化
         *      而 Config1 类中， 有BeanFactoryPostProcessor工厂方法，需要获取这个 BeanFactoryPostProcessor ，就需要有这个 config1对象
         *      这时候执行顺序就是 单例 config 实例化 -> Aware接口 -> 初始化 -> 获取 BeanFactoryPostProcessor -> 注册 BeanFactoryPostProcessor
         *      config 实例化的时候 填充属性， 此时 context 中还没有一些解析 @Autowired 的 BeanFactoryPostProcessor，导致注入失败
         */

        /**
         *  学到了什么：
         *    a. Aware 接口提供了一种「内置」的注入手段，可以注入 BeanFactory，ApplicationContext
         *    b. InitializingBean 接口提供了一种 「内置」的初始化手段
         *    c. 内置的注入和初始化不受扩展功能的影响，总会被执行，因此 Spring 框架内部的类常用他们
         */
    }
}
