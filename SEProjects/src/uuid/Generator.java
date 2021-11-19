package uuid;

import java.util.UUID;

/**
 * @Author: Alivv
 * @Create: 2021-11-19 09:36
 **/
public class Generator {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }
}
