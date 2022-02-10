package com.jalivv.maven.threadpool.starvation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description:
 * @author: Jalivv
 * @date: 2022/2/10 11:57
 */
@Slf4j(topic = "c.Test")
public class Test {

    public static List<String> MENU = Arrays.asList("地三鲜", "锅包肉", "宫保鸡丁", "土豆片");
    static Random random = new Random();
    static String cook(){
        return MENU.get(random.nextInt(MENU.size()));
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService waiter = Executors.newFixedThreadPool(2);
        ExecutorService cooker = Executors.newFixedThreadPool(2);
        waiter.execute(()->{
            Future<String> future = cooker.submit(() -> {
                log.debug("做菜");
                return cook();

            });
            try {
                log.debug("上菜{}",future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        //Thread.sleep(1000L);

        waiter.execute(()->{
            Future<String> future = cooker.submit(() -> {
                log.debug("做菜");
                return cook();

            });
            try {
                log.debug("上菜{}",future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });


    }
}
