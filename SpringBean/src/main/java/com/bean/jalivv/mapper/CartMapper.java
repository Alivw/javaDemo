package com.bean.jalivv.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 08:30
 */
public interface CartMapper {


    @Select("select 'cart'")
    public String selectById();
}
