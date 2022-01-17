package com.jalivv.demo.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:51
 */
@SpringBootApplication
public class MybatisCodeHelperDemoApplication {
    public Logger log = LoggerFactory.getLogger(MybatisCodeHelperDemoApplication.class);

    public MybatisCodeHelperDemoApplication(RedisTemplate<String, Object> redisTemplate) {
        log.info("redistemplate---------->" + redisTemplate.getDefaultSerializer());
        log.info("redistemplate---------->" + redisTemplate.getHashKeySerializer());
        log.info("redistemplate---------->" + redisTemplate.getHashValueSerializer());
    }

    public static void main(String[] args) {
        new SpringApplication(MybatisCodeHelperDemoApplication.class).run(args);
    }


}
