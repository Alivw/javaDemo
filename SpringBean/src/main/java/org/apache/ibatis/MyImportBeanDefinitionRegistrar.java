package org.apache.ibatis;

import com.bean.jalivv.mapper.CartMapper;
import com.bean.jalivv.mapper.OrderMapper;
import com.bean.jalivv.mapper.UserMapper;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
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

        String path = "com.bean.jalivv.mapper";

        Map<String, Object> mapperScanAnnotation = importingClassMetadata.getAnnotationAttributes(JalivvMapperScan.class.getName());
        path = (String) mapperScanAnnotation.get("value");
        JalivvMapperScanner scan = new JalivvMapperScanner(registry);
        scan.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });

        int res = scan.scan(path);


        //List<Class> mapperClass = new ArrayList<>();
        //
        //mapperClass.add(UserMapper.class);
        //mapperClass.add(OrderMapper.class);
        //mapperClass.add(CartMapper.class);
        //
        //for (Class clazz : mapperClass) {
        //    AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //    bd.setBeanClass(MyFactoryBean.class);
        //    bd.getConstructorArgumentValues().addGenericArgumentValue(clazz);
        //    registry.registerBeanDefinition(clazz.getName(), bd);
        //}


    }
}
