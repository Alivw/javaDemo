package com.jalivv.demo.helper.service;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 09:17
 */
public interface RedisTestService {

    /**
     * 往redis里面循环 放入 num 个数据
     *
     * @param num
     */
    void putValueForCircle(Long num);
}
