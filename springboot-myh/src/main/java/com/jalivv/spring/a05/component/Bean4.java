package com.jalivv.spring.a05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Bean4 {


    private static final Logger logger = LoggerFactory.getLogger(Bean4.class);


    public Bean4() {
        logger.debug("我被spring 管理啦");
    }
}
