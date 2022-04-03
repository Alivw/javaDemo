package com.jalivv.spring.a20;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @Description
 * @Date 2022/4/3 09:19
 * @Created by jalivv
 */
@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})
public class WebConfig {
    // 内嵌 web 容器工厂
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(ServerProperties serverProperties) {
        return new TomcatServletWebServerFactory(serverProperties.getPort()==null?8080:serverProperties.getPort());
    }

    // 创建 DispatcherServlet
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

    // 注册 DispatcherServlet ，Spring MVC 的入口
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet, WebMvcProperties mvcProperties) {

        DispatcherServletRegistrationBean db = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        // 设置 tomcat 服务器启动时，完成 DispatcherServlet 的初始化（默认是第一次请求，才会完成 ds 的初始化）
        db.setLoadOnStartup(mvcProperties.getServlet().getLoadOnStartup());

        return db;
    }

/*
        如果用 DispatcherServlet 初始化时默认添加的组件，它只会作为 DispatcherServlet 中一个成员变量，并不会添加到 Spring 容器中。给测试带来困扰
        这里采用手动注入的方式
 */

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
        TokenHandlerMethodArgumentResolver tokenResolver = new TokenHandlerMethodArgumentResolver();
        MyRequestMappingHandlerAdapter handlerAdapter = new MyRequestMappingHandlerAdapter();
        handlerAdapter.setCustomArgumentResolvers(Arrays.asList(tokenResolver));
        return handlerAdapter;
    }


}
