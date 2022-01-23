package com.jalivv.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/22 14:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    private String name;

    private Integer age;

    private Date birthday;

    private PetEntity pet;
}
