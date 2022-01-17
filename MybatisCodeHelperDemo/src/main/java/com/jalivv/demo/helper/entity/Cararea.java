package com.jalivv.demo.helper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description ${DESCRIPTION}
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/17 08:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_cararea")
public class Cararea implements Serializable {
    public static final String COL_ID = "id";
    public static final String COL_AREANAME = "areaName";
    public static final String COL_PRICE = "price";
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "areaName")
    private String areaname;
    @TableField(value = "price")
    private Double price;
}
