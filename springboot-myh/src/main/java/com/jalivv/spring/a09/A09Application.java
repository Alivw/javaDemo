package com.jalivv.spring.a09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description
 * @Date 2022/3/30 12:23
 * @Created by jalivv
 */
@ComponentScan("com.jalivv.spring.a09")
public class A09Application {


    private static final Logger logger = LoggerFactory.getLogger(A09Application.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A09Application.class);
        E e = context.getBean(E.class);
        logger.debug("{}",e.getF1().getClass());
        logger.debug("{}",e.getF1());
        logger.debug("{}",e.getF1());
        logger.debug("{}",e.getF1());

        logger.debug("{}",e.getF2().getClass());
        logger.debug("{}",e.getF2());
        logger.debug("{}",e.getF2());
        logger.debug("{}",e.getF2());

        logger.debug("{}",e.getF3());
        logger.debug("{}",e.getF3());

        logger.debug("{}",e.getF4());
        logger.debug("{}",e.getF4());
    }
}
