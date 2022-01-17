package com.jalivv.demo.helper.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jalivv.demo.helper.entity.User;
import com.jalivv.demo.helper.service.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 09:17
 */
@Service
@Slf4j
public class RedisTestServiceImpl implements RedisTestService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void putValueForCircle(Long num) {

        BoundHashOperations ops = redisTemplate.boundHashOps("test:user");
        long startTime = new Date().getTime();
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("jalivv" + i);
            user.setPassword("jalivvPwd" + i);
            ops.put("user" + i, user);
            //redisTemplate.opsForHash().put("user" + i, "id", user.getId());
            //redisTemplate.opsForHash().put("user" + i, "username", user.getUsername());
            //redisTemplate.opsForHash().put("user" + i, "password", user.getPassword());
        }
        log.info("redis插入200条数据耗时：" + (new Date().getTime() - startTime));
    }
}
