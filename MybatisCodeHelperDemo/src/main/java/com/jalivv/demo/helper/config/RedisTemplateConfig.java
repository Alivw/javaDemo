package com.jalivv.demo.helper.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Description RedisTemplateConfig
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 10:31
 */
@Configuration
public class RedisTemplateConfig {


    private static final Logger logger = LoggerFactory.getLogger(RedisTemplateConfig.class);


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        logger.info("com.jalivv.demo.helper.config-->redisTemplate::redisConnectionFactory = [{}]", redisConnectionFactory);
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setDefaultSerializer(RedisSerializer.json());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
