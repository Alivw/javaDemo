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


    /**
     * 使用 @Autowired 注解 指定spring在实例化过程中使用哪个构造方法
     *
     * @param user
     * @Autowired(required = false) 表示 spring 在实例化过程中可以使用哪个构造方法
     */
    @Autowired(required = false)
    public Student(User user) {
        System.out.println("The constructor of the specified");
    }

    /**
     * 默认先选择参数多的构造方法是否能够实例化
     * 先去 BeanFactory 中按照 ByType 的形式找 User 这个 Bean ，有两个，在按照 ByName 的形式去找，找到一个 user 的Bean
     * 再去按照ByType 的形式找第二个参数，User 有两个，按照ByName确定，没有 user1这个 Bean，则放弃使用这个构造方法
     * <p>
     * 注意：如果构造方法参数个数相同，并且都满足条件，那么就会选择写在前面的构造方法
     *
     * @param user
     * @param user1
     */
    @Autowired(required = false)
    public Student(User user, User user1) {
        System.out.println("two param constructor");
    }

}
