package com.jalivv.spring.a07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @Description
 * @Date 2022/3/30 11:44
 * @Created by jalivv
 */
public class Bean2 implements DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(Bean2.class);

    @PreDestroy
    public void destroy1() {
        logger.debug("销毁1");
    }


    @Override
    public void destroy() throws Exception {
        logger.debug("销毁2");
    }

    public void destroy3() {
        logger.debug("销毁3");
    }
}
