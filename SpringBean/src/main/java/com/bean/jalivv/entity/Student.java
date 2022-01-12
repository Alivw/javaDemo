package com.bean.jalivv.entity;

import com.bean.jalivv.annotation.Jalivv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;

/**
 * @Description TODO
 * @Author jalivv
 * @Date 2021/12/13 21:05
 */

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private User user;

    @Jalivv("ybb")
    private String name;

    private Integer age;

    @Jalivv("a prety girl")
    private String description;

    @Autowired
    public Student(User user) {
        System.out.println("The constructor of the specified");
    }

}
