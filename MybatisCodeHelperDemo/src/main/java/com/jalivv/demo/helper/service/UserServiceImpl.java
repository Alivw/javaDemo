package com.jalivv.demo.helper.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jalivv.demo.helper.entity.User;
import com.jalivv.demo.helper.mapper.UserMapper;
import com.jalivv.demo.helper.service.impl.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description ${DESCRIPTION}
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:44
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}


