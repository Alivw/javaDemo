package com.jalivv.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jalivv.demo.dao")
public class SpringbootProjectsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProjectsApplication.class, args);
    }

}
