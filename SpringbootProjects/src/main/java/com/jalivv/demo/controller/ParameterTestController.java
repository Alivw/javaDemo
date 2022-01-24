package com.jalivv.demo.controller;

import com.jalivv.demo.entity.PersonEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 参数设置controller
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/22 15:37
 */
@RestController
@RequestMapping("/param")
public class ParameterTestController {


    @PostMapping("/person1")
    public PersonEntity person(PersonEntity person) {
        return person;
    }


    @PostMapping("/person2")
    public PersonEntity person2(PersonEntity person) {
        return person;
    }


    @GetMapping("/file")
    public FileSystemResource file() {
        return null;
    }
}
