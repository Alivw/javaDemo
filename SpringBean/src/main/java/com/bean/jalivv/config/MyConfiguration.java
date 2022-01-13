package com.bean.jalivv.config;

import com.bean.jalivv.entity.User;
import org.apache.ibatis.JalivvMapperScan;
import org.apache.ibatis.MyImportBeanDefinitionRegistrar;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/12 19:54
 */
@ComponentScan("com.bean.jalivv.*")
@JalivvMapperScan("com.bean.jalivv.mapper")
public class MyConfiguration {

    @Bean
    SqlSessionFactory sqlSessionFactory() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        return factory;
    }

    @Bean
    public User user2() {
        return new User();
    }
}
