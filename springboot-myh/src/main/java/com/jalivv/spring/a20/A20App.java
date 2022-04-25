package com.jalivv.spring.a20;

import com.ctc.wstx.io.CharsetNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2022/4/3 09:19
 * @Created by jalivv
 */
public class A20App {
    private static final Logger logger = LoggerFactory.getLogger(A20App.class);

    public static void main(String[] args) throws Exception {

        AnnotationConfigServletWebServerApplicationContext ac = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        // DispatcherServlet dispatcherServlet = ac.getBean(DispatcherServlet.class);
        // Method getHandlerMappings = dispatcherServlet.getClass().getDeclaredMethod("getHandlerMappings");
        // }
        // List<HandlerMapping> invoke = (List<HandlerMapping>) getHandlerMappings.invoke(dispatcherServlet, null);
        // for (HandlerMapping handlerMapping : invoke) {
        //     System.out.println(handlerMapping);

        // 作用 解析 @RequestMapping 以及派生注解，生成路径与控制器方法的映射关系，在初始化时就生成
        RequestMappingHandlerMapping requestMappingHandlerMapping = ac.getBean(RequestMappingHandlerMapping.class);
        /*
            RequestMappingInfo:请求参数，请求路径，请求方式 封装在该对象中
            HandlerMethod：哪个控制器的哪个方法
         */
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        handlerMethods.forEach((k, v) -> {
            logger.debug("K:{}==V:{}",k,v);
        });

        /*
            请求来了，获取控制器方法
            HandlerExecutionChain：处理器执行链，封装了 handlerMethods 以及一些拦截器

         */
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test4");
        request.addParameter("name","jalivv");
        request.addHeader("token","jalivvToken666");
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerExecutionChain chain = requestMappingHandlerMapping.getHandler(request);

        // handlerAdapter 作用：调用控制器方法
        MyRequestMappingHandlerAdapter handlerAdapter = (MyRequestMappingHandlerAdapter) ac.getBean("requestMappingHandlerAdapter");
        handlerAdapter.invokeHandlerMethod(request, response, ((HandlerMethod) chain.getHandler()));

        byte[] bytes = response.getContentAsByteArray();
        String s = new String(bytes, StandardCharsets.UTF_8);
        logger.debug("{}",s);

/*
        logger.debug("------------------所有的参数解析器------------------");
        for (HandlerMethodArgumentResolver argumentResolver : handlerAdapter.getArgumentResolvers()) {
            logger.debug("{}", argumentResolver);
        }
        logger.debug("------------------所有的返回值解析器------------------");
        for (HandlerMethodReturnValueHandler returnValueHandler : handlerAdapter.getReturnValueHandlers()) {
            logger.debug("{}",returnValueHandler);
        }
        */
    }
}
