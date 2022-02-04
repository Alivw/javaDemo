package com.jalivv.maven.jmm.Test01;   // 解决package
/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/4 11:26
 */

import lombok.extern.slf4j.Slf4j;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description: 可见性
 * @author:jalivv
 * @date: 2022/2/4 11:26
 */
@Slf4j(topic = "c.Test")
public class Test {

    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag) {
                System.out.println(111);
            }
        }, "t1").start();

        Thread.sleep(1000);
        System.out.println();
        log.debug("改变 flag 为 false");
        flag = false;
    }
}

