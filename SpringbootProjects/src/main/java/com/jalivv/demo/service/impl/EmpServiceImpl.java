package com.jalivv.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jalivv.demo.dao.CategoryDao;
import com.jalivv.demo.dao.EmpDao;
import com.jalivv.demo.entity.Emp;
import com.jalivv.demo.service.EmpService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/24 16:18
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpDao, Emp> implements EmpService {

    private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);


    @Autowired
    EmpDao empDao;


    @Override
    public boolean saveList(List<Emp> emps) throws SQLException {

        logger.info("com.jalivv.demo.service.impl-->saveList::this.basemapper==empDao ? [{}]", empDao == this.baseMapper);

        if (!(empDao.saveList(emps) == emps.size())) {
            throw new SQLException("实际影响条数记录不符");
        }
        return true;

    }
}
