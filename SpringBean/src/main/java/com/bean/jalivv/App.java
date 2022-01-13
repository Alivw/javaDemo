package com.bean.jalivv;

import com.bean.jalivv.config.MyConfiguration;
import com.bean.jalivv.service.StudentService;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws BeansException {
        // 基于注解的 ioc

        //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfiguration.class);
        //StudentService student = (StudentService) ac.getBean("student");
        //User user = ac.getBean("user", User.class);
        //System.out.println(user);
        //student.test();

        ////////////////////////////////////////////////////


        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfiguration.class);

        ac.refresh();

        StudentService bean = ac.getBean("studentService", StudentService.class);

        bean.test();


    }

}
