package com.jalivv.spring.a05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.List;

/**
 * @Description 处理 @Bean 注解解析
 * @Date 2022/3/30 09:20
 * @Created by jalivv
 */
public class AtBeanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        try {
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
                if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                    ((DefaultListableBeanFactory) configurableListableBeanFactory).registerBeanDefinition(annotatedMethod.getMethodName(), beanDefinition);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
