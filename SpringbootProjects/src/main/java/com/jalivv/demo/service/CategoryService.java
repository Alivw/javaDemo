package com.jalivv.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jalivv.demo.entity.CategoryEntity;

import java.util.List;

public interface CategoryService extends IService<CategoryEntity> {


    /**
     * 获取三级分类列表
     * @return
     */
    List<CategoryEntity> getCategoryEntities();
}

