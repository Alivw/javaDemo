package com.bean.jalivv.service.impl;

import com.bean.jalivv.mapper.CartMapper;
import com.bean.jalivv.mapper.OrderMapper;
import com.bean.jalivv.mapper.UserMapper;
import com.bean.jalivv.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 09:11
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartMapper cartMapper;

    @Override
    public void test() {
        userMapper.selectById();
        orderMapper.selectById();
        cartMapper.selectById();
    }
}
