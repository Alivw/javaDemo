package com.jalivv.spring.a03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifeCycleBean {

    private static final Logger logger = LoggerFactory.getLogger(LifeCycleBean.class);

    @Value("jalivvField")
    private String name;

    public LifeCycleBean() {
        logger.debug("构造 LifeCycle");
    }

    @Autowired
    public void autowired(@Value("jalivvMethod") String name) {
        logger.debug("依赖注入：{}",name);
    }

    public String getName() {
        return name;
    }

    @PostConstruct
    public void init(){
        logger.debug("初始化");
    }

    @PreDestroy
    public void destroy(){
        logger.debug("销毁");
    }
}
