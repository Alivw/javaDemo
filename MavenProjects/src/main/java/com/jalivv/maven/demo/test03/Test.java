package com.jalivv.maven.demo.test03;   // 解决package
/**
 * @Description park/unpark
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/4 11:08
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description:
 * @author:jalivv
 * @date: 2022/2/4 11:08
 */
@Slf4j(topic = "c.Test")
public class Test {

    static Thread t1;
    static Thread t2;
    static Thread t3;

    public static void main(String[] args) {
        Park park = new Park(5);

        t1 = new Thread(() -> {
            park.print("a", t2);
        }, "t1");
        t2 = new Thread(() -> {
            park.print("b", t3);
        }, "t2");
        t3 = new Thread(() -> {
            park.print("c", t1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);

    }
}


class Park {
    private int loopNumber;

    public Park(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNumber; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}
