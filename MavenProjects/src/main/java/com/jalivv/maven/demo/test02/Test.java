package com.jalivv.maven.demo.test02;   // 解决package
/**
 * @Description reentrantlock
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/4 10:50
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description:
 * @author:jalivv
 * @date: 2022/2/4 10:50
 */
@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) throws InterruptedException {
        AwaitSingal as = new AwaitSingal(5);
        Condition a = as.newCondition();
        Condition b = as.newCondition();
        Condition c = as.newCondition();

        new Thread(() -> {
            as.print("a", a, b);
        }, "t1").start();
        new Thread(() -> {
            as.print("b", b, c);
        }, "t2").start();
        new Thread(() -> {
            as.print("c", c, a);
        }, "t3").start();

        TimeUnit.SECONDS.sleep(1);

        // 开始
        log.debug("开始....");
        as.lock();
        try {
            a.signal();
        } finally {
            as.unlock();
        }
    }
}


class AwaitSingal extends ReentrantLock {
    private int loopNumber;

    public AwaitSingal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    /**
     * @param str     打印的字符串
     * @param current 目前的休息室
     * @param next    需要唤醒的线程在这个休息室
     */
    public void print(String str, Condition current, Condition next) {
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }
    }
}

