package com.jalivv.spring.a05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            for (String s : componentScan.basePackages()) {
                //com.jalivv.spring.a05.component -> classpath*:com/jalivv/spring/a05/component/**/*.class (** 表示子包)
                String path = "classpath*:" + s.replace(".", "/") + "/**/*.class";
                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                for (Resource resource : resources) {
                    MetadataReader reader = factory.getMetadataReader(resource);
                    if (reader.getAnnotationMetadata().hasAnnotation(Component.class.getName())
                            || reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName())) {
                        AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().getClassName()).getBeanDefinition();
                        if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
                            String name = generator.generateBeanName(bd, beanFactory);
                            beanFactory.registerBeanDefinition(name, bd);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
