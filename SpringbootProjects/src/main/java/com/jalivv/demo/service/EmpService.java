package com.jalivv.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jalivv.demo.entity.CategoryEntity;
import com.jalivv.demo.entity.Emp;

import java.sql.SQLException;
import java.util.List;

public interface EmpService extends IService<Emp> {


    boolean saveList(List<Emp> emps) throws SQLException;
}

