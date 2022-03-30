package com.jalivv.spring.a05;

import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * @Description 模拟@MapperScannerConfigurer
 * @Date 2022/3/30 09:23
 * @Created by jalivv
 */
public class A05Application4 {
    public static void main(String[] args) throws IOException {
        // 创建一个【干净】的容器
        GenericApplicationContext context = new GenericApplicationContext();

        // 将 Config 对象 注册到容器中
        context.registerBean("config", Config.class);

        context.registerBean("atBeanPostProcessor",AtBeanPostProcessor.class);


        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
