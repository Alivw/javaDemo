package com.bean.jalivv;

import com.bean.jalivv.config.MyConfiguration;
import com.bean.jalivv.entity.Student;
import com.bean.jalivv.entity.User;
import com.bean.jalivv.service.StudentService;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws BeansException {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfiguration.class);
        StudentService student = (StudentService) ac.getBean("student");
        User user = ac.getBean("user", User.class);
        System.out.println(user);
        student.test();

    }

}
