package com.jalivv.spring.a06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Date 2022/3/30 11:15
 * @Created by jalivv
 */
@Configuration
public class Config1 {


    private static final Logger logger = LoggerFactory.getLogger(Config1.class);


    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        logger.debug("{}", context);
    }

    @PostConstruct
    public void init(){
        logger.debug("init");
    }

    @Bean
    public  BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return beanFactory -> logger.debug("BeanFactoryPostProcessor 执行");
    }

}
