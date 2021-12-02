package com.jalivv.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jalivv.demo.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
