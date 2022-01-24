package com.jalivv.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/24 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private double sal;

    private double comm;

    private Integer deptno;
}
