package com.jalivv.demo.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.temporal.Temporal;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:51
 */
@SpringBootApplication
public class MybatisCodeHelperDemoApplication {


    private static final Logger logger = LoggerFactory.getLogger(MybatisCodeHelperDemoApplication.class);


    public MybatisCodeHelperDemoApplication(RedisTemplate<String, Object> redisTemplate) {

        logger.info("com.jalivv.demo.helper-->MybatisCodeHelperDemoApplication::redisTemplate:" +
                        "DefaultSerializer = [{}],HashKeySerializer = [{}],HashValueSerializer = [{}]",
                redisTemplate.getDefaultSerializer(), redisTemplate.getHashKeySerializer(), redisTemplate.getHashValueSerializer());
    }

    public static void main(String[] args) {
        new SpringApplication(MybatisCodeHelperDemoApplication.class).run(args);
    }


}
