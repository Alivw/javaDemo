package com.bean.jalivv.config;

import com.bean.jalivv.entity.User;
import com.bean.jalivv.importregistrar.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/12 19:54
 */
@ComponentScan("com.bean.jalivv.*")
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyConfiguration {


    @Bean
    public User user2() {
        return new User();
    }
}
