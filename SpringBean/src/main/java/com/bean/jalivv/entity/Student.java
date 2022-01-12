package com.bean.jalivv.entity;

import com.bean.jalivv.annotation.Jalivv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author jalivv
 * @Date 2021/12/13 21:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {

    @Jalivv("ybb")
    private String name;

    private Integer age;

    @Jalivv("a prety girl")
    private String description;

}
