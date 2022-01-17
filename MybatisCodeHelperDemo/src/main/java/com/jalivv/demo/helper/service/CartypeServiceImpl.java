package com.jalivv.demo.helper.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jalivv.demo.helper.entity.Cartype;
import com.jalivv.demo.helper.mapper.CartypeMapper;
import com.jalivv.demo.helper.service.impl.CartypeService;
import org.springframework.stereotype.Service;

/**
 * @Description ${DESCRIPTION}
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:44
 */
@Service
public class CartypeServiceImpl extends ServiceImpl<CartypeMapper, Cartype> implements CartypeService {

}


