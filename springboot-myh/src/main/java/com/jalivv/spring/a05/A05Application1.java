package com.jalivv.spring.a05;

import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class A05Application1 {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);


        context.registerBean(ComponentScanPostProcessor.class);


        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
