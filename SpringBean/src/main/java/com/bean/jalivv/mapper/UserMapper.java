package com.bean.jalivv.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/13 08:30
 */
public interface UserMapper {


    @Select("select 'user'")
    String selectById();
}
