package com.jalivv.spring.a05;

import com.alibaba.druid.pool.DruidDataSource;
import com.jalivv.spring.a05.mapper.Mapper1;
import com.jalivv.spring.a05.mapper.Mapper2;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.jalivv.spring.a05.component")
public class Config {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public SqlSessionFactoryBean SqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;

    }


    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://service.aliww.top:3306/yang");
        dataSource.setUsername("root");
        dataSource.setPassword("jalivv666");
        return dataSource;
    }

    @Bean
    public MapperFactoryBean<Mapper1> mapper1(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper1> mapper1 = new MapperFactoryBean(Mapper1.class);
        mapper1.setSqlSessionFactory(sqlSessionFactory);
        return mapper1;
    }

    @Bean
    public MapperFactoryBean<Mapper2> mapper2(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<Mapper2> mapper2 = new MapperFactoryBean(Mapper2.class);
        mapper2.setSqlSessionFactory(sqlSessionFactory);
        return mapper2;
    }


}
