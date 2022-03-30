package com.jalivv.spring.a08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @Description
 * @Date 2022/3/30 12:09
 * @Created by jalivv
 */
@Scope("application")
@Component
public class Bean4Application {

    private static final Logger logger = LoggerFactory.getLogger(Bean4Application.class);

    @PreDestroy
    public void destroy() {
        logger.debug("{}--->destroy",this);
    }
}
