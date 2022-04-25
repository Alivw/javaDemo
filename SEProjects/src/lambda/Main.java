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
