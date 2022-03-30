package com.jalivv.spring.a05;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 模拟解析 @ComponentScan
 */
public class A05Application {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("config", Config.class);
        /**
         * ConfigurationClassPostProcessor： 一个 BeanFactoryPostProcessor ，能够解析 @ComponentScan @Bean @Import @ImportResource
         */
        //context.registerBean(ConfigurationClassPostProcessor.class);
        //
        //
        //context.registerBean(MapperScannerConfigurer.class,bd -> {
        //    bd.getPropertyValues().add("basePackage", "com.jalivv.spring.a05.mapper");
        //}); // @MapperScan

        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);

        for (String s : componentScan.basePackages()) {
            //com.jalivv.spring.a05.component -> classpath*:com/jalivv/spring/a05/component/**/*.class (** 表示子包)
            String path = "classpath*:" + s.replace(".", "/") + "/**/*.class";
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
            DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

            Resource[] resources = context.getResources(path);
            for (Resource resource : resources) {
                //System.out.println(resource);
                MetadataReader reader = factory.getMetadataReader(resource);
                //System.out.println("类名：" + reader.getClassMetadata().getClassName());
                //System.out.println("是否加了 @Component："+reader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
                //System.out.println("是否加了 @Component 派生注解："+reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));

                if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())
                        || reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {
                    AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().getClassName()).getBeanDefinition();
                    String name = generator.generateBeanName(bd, beanFactory);
                    beanFactory.registerBeanDefinition(name, bd);
                }

            }
        }

        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
