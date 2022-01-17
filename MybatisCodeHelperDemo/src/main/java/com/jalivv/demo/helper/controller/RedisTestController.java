package com.jalivv.demo.helper.controller;

import com.jalivv.demo.helper.service.RedisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 09:08
 */
@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    RedisTestService redisTestService;

    @GetMapping("/put/{num}")
    public String put(@PathVariable("num") Long num) {
        redisTestService.putValueForCircle(num);
        return "ok";
    }
}
