package com.bean.jalivv;

import com.bean.jalivv.config.MyConfiguration;
import com.bean.jalivv.entity.Student;
import com.bean.jalivv.entity.User;
import com.bean.jalivv.importregistrar.MyImportBeanDefinitionRegistrar;
import com.bean.jalivv.mapper.CartMapper;
import com.bean.jalivv.mapper.UserMapper;
import com.bean.jalivv.service.StudentService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.apache.ibatis.MyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

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
