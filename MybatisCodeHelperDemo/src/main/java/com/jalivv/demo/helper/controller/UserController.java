package com.jalivv.demo.helper.controller;

import com.jalivv.demo.helper.entity.User;
import com.jalivv.demo.helper.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }
}
