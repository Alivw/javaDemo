package com.jalivv.demo.controller;

import com.jalivv.demo.entity.CategoryEntity;
import com.jalivv.demo.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Jalivv
 * @Email: shizuwei99@163.com
 * @Create: 2021-12-02 10:21
 */

@RestController()
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public List<CategoryEntity> getCategoryEntities() {

        return categoryService.getCategoryEntities();

    }


}
