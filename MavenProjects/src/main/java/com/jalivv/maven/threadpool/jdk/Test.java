package com.jalivv.maven.threadpool.jdk;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
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
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //test3(pool);
        //test4(pool);
    }

    /**
     * invokeAny 一个任务集合，如果有任务执行成功了，就返回，其他的任务不会执行
     * @param pool
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void test4(ExecutorService pool) throws InterruptedException, ExecutionException {
        Object o = pool.invokeAny(Arrays.asList(
                ()->{
                    return 1;
                },
                ()->{
                    return 2;
                },
                ()->{
                    return 3;
                }
        ));
        log.debug("{}",o);
    }

    /**
     * invokeAll方法
     * @param pool
     * @throws InterruptedException
     */
    private static void test3(ExecutorService pool) throws InterruptedException {
        List<Future<Integer>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("task1...");
                    return 1;
                },
                () -> {
                    log.debug("task2....");
                    return 2;
                },
                () -> {
                    log.debug("task3....");
                    return 3;
                }
        ));
        futures.forEach(item -> {
            try {
                log.debug("{}", item.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * submit 方法
     *
     * @throws Exception
     */
    private static void test2() throws Exception {
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
            pool.execute(() -> {
                log.debug("....." + j);
            });
        }
        new Thread(() -> {
        }, "t1").start();
    }
}
