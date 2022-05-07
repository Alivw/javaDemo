package lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description lambda 表达式运用
 * @Date 2022/4/26 10:21
 * @Created by jalivv
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
            User user = new User("jalivv" + i, 18 + i, "sql boy:" + i);
            userList.add(user);
        }

        // 遍历 stream
        list.stream().forEach(System.out::println);



        Map<Integer, Integer> collect = list.stream().collect(Collectors.toMap(i -> i, i -> i));

        for (Integer key : collect.keySet()) {
            System.out.printf("key:%d === val:%d\n",key,collect.get(key));
        }


        Map<String, Integer> userMap = userList.stream().collect(Collectors.toMap(User::getName, User::getAge));

        for (Map.Entry<String, Integer> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + "===" + entry.getValue());
        }
    }
}


class User{
    private String name;
    private int age;
    private String desc;

    public User(String name, int age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
