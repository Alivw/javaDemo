package com.jalivv.demo;

import annotaion.EnableJalivv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import template.JalivvTemplate;

@EnableJalivv
@SpringBootApplication
public class SpringbootProjectsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootProjectsApplication.class, args);
        JalivvTemplate jalivvTemplate = run.getBean("jalivvTemplate", JalivvTemplate.class);
        jalivvTemplate.say();
    }

}
