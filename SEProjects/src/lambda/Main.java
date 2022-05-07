package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Description
 * @Date 2022/4/25 21:00
 * @Created by jalivv
 */
public class Main {


    /*
            方法引用：若Lambda 体中的内容有方法已经实现了，我们可以使用”方法引用”
            （可以理解为方法引用是 Lambda 表达式的另外一种表现形式）
            主要有三种语法格式：
                对象：：实例方法名
                类：：静态方法名
                类：：实例方法名
            1、@Lambda 体中调用方法的参数列表与返回值类型，要与西数式接口中抽家方法的函数列表和返回值类型保持一致！
            2、若 Lambda 参数列表中的第一参数是 实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 Clas sName : : method
     */

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("jalivv");
        test(student, student::getName);

        System.out.printf("%2s","1");

        List<Integer> numList = getNumList(10, ( () ->(int) (Math.random() * 100)));
        for (Integer num : numList) {
            System.out.print(num+" ");
        }

    }


    public static void test(Student s ,MyInterface inter) {
        System.out.println(((String) inter.accept()));
    }

    public static List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = sup.get();
            list.add(integer);
        }
        return list;
    }
}


interface MyInterface<T>{
   T accept();
}

class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
