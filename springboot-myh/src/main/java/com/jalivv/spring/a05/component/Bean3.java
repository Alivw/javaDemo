package com.jalivv.spring.a05.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class Bean3 {


    private static final Logger logger = LoggerFactory.getLogger(Bean3.class);


    public Bean3() {
        logger.debug("我被spring 管理啦");
    }
}
