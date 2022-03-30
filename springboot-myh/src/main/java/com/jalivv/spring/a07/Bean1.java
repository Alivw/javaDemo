package com.jalivv.spring.a07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Date 2022/3/30 11:39
 * @Created by jalivv
 */
public class Bean1 implements InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(Bean1.class);


    @PostConstruct
    public void init1(){
        logger.debug("初始化1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("初始化2");
    }

    public void init3() {
        logger.debug("初始化3");
    }
}
