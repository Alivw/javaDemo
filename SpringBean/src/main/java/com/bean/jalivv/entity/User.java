package com.bean.jalivv.entity;

import com.bean.jalivv.annotation.Jalivv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/1/12 20:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {

    @Jalivv
    private String name;
}
