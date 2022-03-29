package com.jalivv.spring.a03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class A03Application { 


    private static final Logger logger = LoggerFactory.getLogger(A03Application.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A03Application.class, args);
        System.out.println(context.getBean(LifeCycleBean.class).getName());
        context.close();
    }
}
