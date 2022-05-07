package lambda;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Description
 * @Date 2022/4/26 09:36
 * @Created by jalivv
 */
public class Work {


    /*
        调用 Collections.sort() 方法，通过定制排序比较两个 Employee(先按年龄比，年龄相同
        按姓名比)，使用 Lambda 作为參数传递。
        ①声明函数式接口，接口中声明抽象方法，public string getvalue(string str);-
        ②声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，
        并作为方法的返回值。
        ③再将一个宇符串的第2个和第4 个素引位置进行截取子串。
        ①声明一个带两个泛型的西数式接口，泛型类型为<T,R> 为参数，R 为返回值。
        ②接口中声明对应抽象方法
        ③在 Testlambda 类中声明方法，使用接口作为参数，计算两个 long 型參数的和。
        西再计算两个 long 型參数的乘积。
     */
    public static void main(String[] args) {
        // 声明类 TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
        Function<String,String> fu =String::toUpperCase;
        System.out.println(fu.apply("jalivv 666"));

        // 再将一个宇符串的第2个和第4 个素引位置进行截取子串。
        String s = "hello";

        BiFunction<Long, Long, Long> fun = (x, y) -> x + y;
        System.out.println(fun.apply(10L, 20L));

    }

}

class Employee {
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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
