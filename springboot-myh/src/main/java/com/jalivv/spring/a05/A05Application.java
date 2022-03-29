package com.jalivv.spring.a05;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

public class A05Application {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);
        /**
         * ConfigurationClassPostProcessor： 一个 BeanFactoryPostProcessor ，能够解析 @ComponentScan @Bean @Import @ImportResource
         */
        context.registerBean(ConfigurationClassPostProcessor.class);


        context.registerBean(MapperScannerConfigurer.class,bd -> {
            bd.getPropertyValues().add("basePackage", "com.jalivv.spring.a05.mapper");
        }); // @MapperScan

        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
