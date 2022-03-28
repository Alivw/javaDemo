package com.jalivv.spring.a02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class A02Application {


    private static final Logger logger = LoggerFactory.getLogger(A02Application.class);


    public static void main(String[] args) {
        //testClassPathXmlApplicationContext();

        //testFileSystemXmlApplicationContext();

        testAnnotationConfigApplicationContext();
    }

    private static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        for (String name : context.getBeanDefinitionNames()) {
            logger.debug("{}", name);
        }

        logger.debug("{}", context.getBean(Bean2.class).getBean1());
    }

    // 较为经典的容器， 基于 classpath 下 xml 格式的配置文件来创建
    private static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("b01.xml");
        for (String name : context.getBeanDefinitionNames()) {
            logger.debug("{}", name);
        }

        logger.debug("{}",context.getBean(Bean2.class).getBean1());
    }

    // 基于磁盘路径下 xml 格式的配置文件来创建
    private static void testFileSystemXmlApplicationContext() {
        FileSystemXmlApplicationContext context =
                new FileSystemXmlApplicationContext("src/main/resources/b01.xml");
        for (String name : context.getBeanDefinitionNames()) {
            logger.debug("{}", name);
        }

        logger.debug("{}", context.getBean(Bean2.class).getBean1());
    }


    static class Configuration{

       @Bean
       public Bean1 bean1(){
           return new Bean1();
       }

        @Bean
        public Bean2 bean2(Bean1 bean1) {
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }
    }


    static class Bean1{

    }


    static class Bean2{
        private Bean1 bean1;

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }


}
