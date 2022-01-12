package com.bean.jalivv;

import com.bean.jalivv.config.MyConfiguration;
import com.bean.jalivv.entity.Student;
import com.bean.jalivv.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfiguration.class);
        Student student = (Student) ac.getBean("student", Student.class);
        User user = ac.getBean("user", User.class);
        System.out.println(user);
        System.out.println(student);
    }

}
