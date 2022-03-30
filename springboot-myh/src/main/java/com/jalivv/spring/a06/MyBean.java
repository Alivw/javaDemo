package com.jalivv.spring.a06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Date 2022/3/30 10:17
 * @Created by jalivv
 */
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(MyBean.class);


    @Override
    public void setBeanName(String name) {
        logger.debug("{}的名字：{} ", this, name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("注入的容器是：{}",applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("{}初始化",this);
    }


    @Autowired
    public void autowired(ApplicationContext context) {
        logger.debug("@Autowired 注入的容器是：{}",context);
    }

    @PostConstruct
    public void init() {
        logger.debug("PostConstruct{}初始化",this);
    }
}
