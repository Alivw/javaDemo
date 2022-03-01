package uuid;

import java.io.IOException;
import java.io.InputStream;
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


    public static void main(String[] args) throws IOException, InterruptedException {

        //String[] foods = {"泡面", "烤肉", "麻辣烫", "鸡公煲", "火鸡面"};
        //
        //int index = (int) (Math.random() * 10) % foods.length;
        //
        //System.out.println("今日晚餐:" + foods[index]);

        //
        //long id = 0x7FFFFFFFFFFFFFFFL;
        //long pid = 0x7FFFFFFF00000000L;
        //
        //int pidInt = (int) pid;
        //System.out.println("pidIt=" + pidInt);
        //System.out.println(pid == 0);



        ///////////
        Process process = Runtime.getRuntime().exec("arp -a");
        //process.waitFor();
        InputStream in = process.getInputStream();
        StringBuilder result = new StringBuilder();
        byte[] data = new byte[256];
        while(in.read(data) != -1){
            //String encoding = System.getProperty("sun.jnu.encoding");
            String s = new String(data, "utf-8");
            String ip = s.substring(s.indexOf("("), s.indexOf(")"));
            String mac = s.substring(s.indexOf("at") + 1, s.indexOf("on") - 1);

            result.append(ip).append("-"+mac).append("\n");
        }
        System.out.println(result);
    }
}
