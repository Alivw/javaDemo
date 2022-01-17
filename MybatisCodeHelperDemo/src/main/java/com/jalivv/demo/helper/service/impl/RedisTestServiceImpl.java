package com.jalivv.demo.helper.service.impl;

import com.jalivv.demo.helper.entity.User;
import com.jalivv.demo.helper.service.RedisTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
        for (int i = 0; i < num; i++) {
            User user = new User();
            user.setId(i);
            user.setUsername("jalivv" + i);
            user.setPassword("jalivvPwd" + i);
            ops.put("user" + i, user);
        }
    }
}
