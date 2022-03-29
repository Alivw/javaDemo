package com.jalivv.spring.a05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bean1 {

    private static final Logger logger = LoggerFactory.getLogger(Bean1.class);


    public Bean1() {
        logger.debug("我被 spring 管理啦");
    }
}
