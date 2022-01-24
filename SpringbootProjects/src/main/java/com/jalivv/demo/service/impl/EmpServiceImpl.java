package com.jalivv.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jalivv.demo.dao.CategoryDao;
import com.jalivv.demo.dao.EmpDao;
import com.jalivv.demo.entity.Emp;
import com.jalivv.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/24 16:18
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, Emp> implements EmpService {

    @Autowired
    EmpDao empDao;

    @Override
    public void saveList(List<Emp> emps) {
        empDao.saveList(emps);
    }
}
