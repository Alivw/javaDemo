package com.jalivv.demo;

import com.sun.tools.hat.internal.parser.ReadBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import template.JalivvTemplate;

import javax.annotation.Resource;

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
