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

public class A05Application2 {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);

        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        MetadataReader reader = cachingMetadataReaderFactory.getMetadataReader(new ClassPathResource("com/jalivv/spring/a05/Config.class"));
        for (MethodMetadata annotatedMethod : reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName())) {
            System.out.println(annotatedMethod);
            //String initMethod = annotatedMethod.getAllAnnotationAttributes(Bean.class.getName()).get("initMethod").toString();
            List<Object> initMethod = annotatedMethod.getAllAnnotationAttributes(Bean.class.getName()).get("initMethod");
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            // 那个对象(config)来调用这些方法 生成 bean
            builder.setFactoryMethodOnBean(annotatedMethod.getMethodName(), "config");
            // 指定装配模式 方法参数是需要装配的
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            for (Object o : initMethod) {
                if (o instanceof String) {
                    builder.setInitMethodName((String) o);
                }
            }
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
