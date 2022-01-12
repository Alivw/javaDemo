package uuid;

import java.util.UUID;

/**
 * @Author: Alivv
 * @Create: 2021-11-19 09:36
 **/
public class Generator {

    public static void main1(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }


    public static void main(String[] args) {

        String[] foods = {"泡面", "烤肉", "麻辣烫", "鸡公煲", "火鸡面"};

        int index = (int) (Math.random() * 10) % foods.length;

        System.out.println("今日晚餐:" + foods[index]);
    }
}
