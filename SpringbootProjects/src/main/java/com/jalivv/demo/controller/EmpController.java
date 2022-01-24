package com.jalivv.demo.controller;

import com.jalivv.demo.entity.Emp;
import com.jalivv.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/24 16:25
 */
@RestController
public class EmpController {

    @Autowired
    EmpService empService;

    @PostMapping("/emp")
    public String addEmp(@RequestBody List<Emp> emps) {
        empService.saveList(emps);
        return "ok";
    }
}
