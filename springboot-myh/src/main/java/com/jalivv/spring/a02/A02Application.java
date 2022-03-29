package com.jalivv.spring.a02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class A02Application {


    private static final Logger logger = LoggerFactory.getLogger(A02Application.class);


    public static void main(String[] args) {
        //testClassPathXmlApplicationContext();

        //testFileSystemXmlApplicationContext();

        //testAnnotationConfigApplicationContext();

        testAnnotationConfigServletWebServerApplicationContext();

    }

    // 较为经典的容器，基于 java 配置类来创建， 用于 web 环境
    private static void testAnnotationConfigServletWebServerApplicationContext() {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfiguration.class);


    }

    private static void testAnnotationConfigApplicationContext()  {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
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


    @Configuration
    static class WebConfiguration{

        /**
         * web 服务组件
         * @return
         */
        @Bean
        public ServletWebServerFactory servletWebServerFactory() {
            return new TomcatServletWebServerFactory();
        }

        /**
         * web 请求中央调度器
         * @return
         */
        @Bean
        public DispatcherServlet dispatcherServlet(){
            return new DispatcherServlet();
        }


        /**
         * 将 tomcat 跟 dispatherServlet 关联起来
         * "/"  所有请求都经过 dispatcherServlet
         * @param dispatcherServlet
         * @return
         */
        @Bean
        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet){
            return new DispatcherServletRegistrationBean(dispatcherServlet,"/");
        }


        /**
         * 这是一个 controller ，@Bean 名字以斜杠开头，就是 访问路径
         * @return
         */
        @Bean("/hello")
        public Controller controller1(){
            return (request, response) -> {
                response.getWriter().write("hello");
                return null;
            };
        }

    }

    static class Config{

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
