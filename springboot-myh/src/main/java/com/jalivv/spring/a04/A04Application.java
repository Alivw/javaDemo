package com.jalivv.spring.a04;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

public class A04Application {
    public static void main(String[] args) {
        // GenericApplicationContext 是一个 「干净」的容器，没有 BeanFactoryPostProcessor, BeanPostProcessor
        GenericApplicationContext context = new GenericApplicationContext();

        // 用原始方法注册三个 Bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        context.getDefaultListableBeanFactory()
                .setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());    //@Autowired  方法参数中 @Value 的值

        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);   //  解析 @Autowired @Value
        context.registerBean(CommonAnnotationBeanPostProcessor.class);  // @Resource @PostConstruct @PreDestroy

        // 解析 @ConfigurationProperties
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());

        //  初始化容器
        context.refresh(); // 执行 BeanFactoryPostProcessor ,添加 BeanPostProcessor ,初始化所有单例


        System.out.println(context.getBean(Bean4.class));
        // 销毁容器
        context.close();

    }
}
