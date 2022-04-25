package lambda;

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

    }


    public static void test(Student s ,MyInterface inter) {
        System.out.println(((String) inter.accept()));
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
