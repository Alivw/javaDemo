package com.jalivv.spring.a05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Bean2 {


    private static final Logger logger = LoggerFactory.getLogger(Bean2.class);


    public Bean2() {
        logger.debug("我被spring 管理啦");
    }
}
