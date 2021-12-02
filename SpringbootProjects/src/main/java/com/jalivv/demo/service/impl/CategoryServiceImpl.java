package com.jalivv.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jalivv.demo.dao.CategoryDao;
import com.jalivv.demo.entity.CategoryEntity;
import com.jalivv.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<CategoryEntity> getCategoryEntities() {

        // 查出所有的分类
        List<CategoryEntity> allCategories = super.baseMapper.selectList(null);
        // 找到所有的一级分类
        List<CategoryEntity> menuTree = allCategories.stream().filter(e -> e.getParentCid() == 0).map(menu -> {
            menu.setChildren(getChildren(menu, allCategories));
            return menu;
        }).sorted((m1, m2) -> {
            return (m1.getSort() == null ? 0 : m1.getSort()) - (m2.getSort() == null ? 0 : m2.getSort());
        }).collect(Collectors.toList());
        return menuTree;

    }

    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> list = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(root.getCatId());
        }).map(categoryEntity -> {
            // 找到子菜单（递归）
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu, menu2) -> {
            //2、菜单的排序
            return (menu.getSort() == null ? 0 : menu.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return list;
    }

}
