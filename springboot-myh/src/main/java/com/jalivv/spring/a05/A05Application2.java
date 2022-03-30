package com.jalivv.spring.a05;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.List;

/**
 * 模拟解析 @Bean
 */
public class A05Application2 {
    public static void main(String[] args) throws IOException {
        // 创建一个【干净】的容器
        GenericApplicationContext context = new GenericApplicationContext();

        // 将 Config 对象 注册到容器中
        context.registerBean("config", Config.class);

        // 获取读取类信息的reader
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        MetadataReader reader = cachingMetadataReaderFactory.getMetadataReader(new ClassPathResource("com/jalivv/spring/a05/Config.class"));

        // 从封装的 reader 中，读取到 被 @Bean 标记的方法
        for (MethodMetadata annotatedMethod : reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName())) {
            System.out.println(annotatedMethod);
            // 获取 初始化方法
            List<Object> initMethod = annotatedMethod.getAllAnnotationAttributes(Bean.class.getName()).get("initMethod");
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            // 哪个对象(config)来调用这些方法 生成 bean
            builder.setFactoryMethodOnBean(annotatedMethod.getMethodName(), "config");
            // 指定装配模式  默认是 AutowireCapableBeanFactory.AUTOWIRE_NO ，遇到方法参数 不自动装配，直接跳过
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            for (Object o : initMethod) {
                if (o instanceof String) {
                    builder.setInitMethodName((String) o);
                }
            }
            // 注册到 BeanFactory 中
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            context.getDefaultListableBeanFactory().registerBeanDefinition(annotatedMethod.getMethodName(),beanDefinition);
        }
        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
