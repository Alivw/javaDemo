package com.jalivv.spring.a04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

public class Bean1 {

    private static final Logger logger = LoggerFactory.getLogger(Bean1.class);

    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2) {
        logger.debug("@Autowired 生效：{}", bean2);
    }

    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3) {
        logger.debug("@Resource 生效：{}", bean3);
    }

    private String home;

    @Autowired
    private void setHome(@Value("${java.home}") String home) {
        logger.debug("@Value 生效：{}", home);
        this.home = home;
    }

    @PostConstruct
    public void init() {
        logger.debug("@PostConstruct 生效");
    }

    @PreDestroy
    public void destroy() {
        logger.debug("@PreDestroy 生效");
    }


    @Override
    public String  toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                ", home='" + home + '\'' +
                '}';
    }
}
