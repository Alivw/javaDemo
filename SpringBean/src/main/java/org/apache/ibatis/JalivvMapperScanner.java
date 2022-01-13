package org.apache.ibatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Set;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 09:40
 */
public class JalivvMapperScanner extends ClassPathBeanDefinitionScanner {


    public JalivvMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> holders = super.doScan(basePackages);
        try {
            for (BeanDefinitionHolder bdh : holders) {
                GenericBeanDefinition bd = (GenericBeanDefinition) bdh.getBeanDefinition();
                bd.getConstructorArgumentValues().addGenericArgumentValue(Class.forName(bd.getBeanClassName()));
                bd.setBeanClassName(MyFactoryBean.class.getName());
                bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return holders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
