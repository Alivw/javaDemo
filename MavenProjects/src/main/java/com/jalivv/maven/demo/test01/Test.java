package com.jalivv.maven.demo.test01;   // 解决package
/**
 * @Description 三个线程交替输出a b c
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/3 10:43
 */

import lombok.extern.slf4j.Slf4j;


/**
 * @Description:
 * @author:jalivv
 * @date: 2022/2/3 10:43
 */
@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(1, 3);
        new Thread(() -> {
            waitNotify.print("a", 1, 2);
        }, "t1").start();

        new Thread(() -> {
            waitNotify.print("b", 2, 3);
        }, "t2").start();

        new Thread(() -> {
            waitNotify.print("c", 3, 1);
        }, "t3").start();
    }
}

class WaitNotify {
    private int waitflag;
    private int loopNumber;
    public WaitNotify(int waitflag, int loopNumber) {
        this.waitflag = waitflag;
        this.loopNumber = loopNumber;
    }
    public void print(String str, int flag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while (waitflag != flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                waitflag = nextFlag;
                this.notifyAll();
            }
        }

    }
}
