package com.jalivv.demo.controller;

import com.jalivv.demo.entity.Emp;
import com.jalivv.demo.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);


    @Autowired
    EmpService empService;

    @PostMapping("/emp")
    public String addEmp(@RequestBody List<Emp> emps) {
        try {
            empService.saveList(emps);
            return "ok";
        } catch (Exception e) {
            logger.error("保存出错:", e);
            return "error";
        }
    }
}
