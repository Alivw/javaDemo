package com.jalivv.maven.threadpool.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description:
 * @author: Jalivv
 * @date: 2022/2/10 09:52
 */
@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(4);
        Future<String> future = pool.submit(() -> {
            log.debug("开始执行....");
            Thread.sleep(1000L);
            return "ok";
        });

        log.debug("{}", future.get());

    }

    /**
     * 固定大小的线程池
     */
    private static void test1() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            int j = i;
            pool.execute(()->{
                log.debug("....." + j);
            });
        }
        new Thread(() -> {
        }, "t1").start();
    }
}
