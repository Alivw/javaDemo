package com.jalivv.spring.a39;

import com.jalivv.spring.a04.Bean3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Description
 * @Date 2022/4/4 11:32
 * @Created by jalivv
 */
public class A39_1 {
    public static void main(String[] args) throws Exception {

        System.out.println("1. 演示 获取 BeanDefinition 源");
        SpringApplication spring = new SpringApplication(A39_1.class);
        spring.setSources(new HashSet<>(Arrays.asList("classpath:b01.xml")));

        System.out.println("2. 演示 推断应用类型");
        Method deduceMethod = WebApplicationType.class.getDeclaredMethod("deduceFromClasspath");
        deduceMethod.setAccessible(true);
        Object invoke = deduceMethod.invoke(null);
        System.out.println("应用类型："+invoke);
        System.out.println("3. 演示 ApplicationContext 初始化器");
        spring.addInitializers(applicationContext -> {
            if (applicationContext instanceof GenericApplicationContext) {
                GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;
                genericApplicationContext.registerBean("bean3", Bean3.class);
            }
        });
        System.out.println("4. 演示 监听器与事件");
        spring.addListeners(event -> {
            System.out.println("事件为："+event.getClass());
        });
        System.out.println("5. 演示 主类推断");

        Method deduceMainApplicationClass = SpringApplication.class.getDeclaredMethod("deduceMainApplicationClass");
        deduceMainApplicationClass.setAccessible(true);
        Object invoke1 = deduceMainApplicationClass.invoke(spring, null);
        System.out.println("主类是："+invoke1);
        ConfigurableApplicationContext context = spring.run(args);


        /*
            创建 ApplicationContext
            调用初始化器 对 ApplicationContext 进行扩展
            ApplicationContext.refresh
         */

        for (String name : context.getBeanDefinitionNames()) {
            String resourceDescription = context.getBeanFactory().getBeanDefinition(name).getResourceDescription();

            System.out.println("name:" + name + "  来源：" + resourceDescription);
        }

        context.close();
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        return new TomcatServletWebServerFactory();
    }
}
