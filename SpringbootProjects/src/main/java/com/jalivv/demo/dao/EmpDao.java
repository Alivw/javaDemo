package com.jalivv.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jalivv.demo.entity.CategoryEntity;
import com.jalivv.demo.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品三级分类
 */
@Mapper
public interface EmpDao extends BaseMapper<Emp> {

    void saveList(List<Emp> emps);
}
