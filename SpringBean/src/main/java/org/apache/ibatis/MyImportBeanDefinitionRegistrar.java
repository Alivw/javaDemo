package org.apache.ibatis;

import com.bean.jalivv.mapper.CartMapper;
import com.bean.jalivv.mapper.OrderMapper;
import com.bean.jalivv.mapper.UserMapper;
import org.apache.ibatis.MyFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 09:25
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        String path = "com.ben.jalivv.mapper";

        List<Class> mapperClass = new ArrayList<>();

        mapperClass.add(UserMapper.class);
        mapperClass.add(OrderMapper.class);
        mapperClass.add(CartMapper.class);

        for (Class clazz : mapperClass) {
            AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
            bd.setBeanClass(MyFactoryBean.class);
            bd.getConstructorArgumentValues().addGenericArgumentValue(clazz);
            registry.registerBeanDefinition(clazz.getName(), bd);
        }


    }
}
