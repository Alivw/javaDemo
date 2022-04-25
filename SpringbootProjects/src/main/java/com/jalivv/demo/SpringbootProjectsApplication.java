package com.jalivv.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import template.JalivvTemplate;

//@EnableJalivv
@SpringBootApplication
public class SpringbootProjectsApplication {


    private static final Logger logger = LoggerFactory.getLogger(SpringbootProjectsApplication.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootProjectsApplication.class, args);
        JalivvTemplate jalivvTemplate = run.getBean("jalivvTemplate", JalivvTemplate.class);
        jalivvTemplate.say();


    }
}
