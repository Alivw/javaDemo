package com.jalivv.demo.controller;

import com.jalivv.demo.entity.PersonEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 参数设置controller
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/22 15:37
 */
@RestController
@RequestMapping("/param")
public class ParameterTestController {


    @PostMapping("/person")
    public PersonEntity person(PersonEntity person) {
        return person;
    }


    @GetMapping("/file")
    public FileSystemResource file() {
        return null;
    }
}
