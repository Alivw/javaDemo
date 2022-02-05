package com.jalivv.maven.jmm.volatilet;   // 解决package
/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/4 11:56
 */

import lombok.extern.slf4j.Slf4j;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description:
 * @author:jalivv
 * @date: 2022/2/4 11:56
 */
@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) {
        Clazz clazz = new Clazz();
        clazz.start();

        clazz.start();
    }
}

@Slf4j(topic = "c.Clazz")
class Clazz {
    public boolean starting = false;

    public void start() {
        synchronized (this) {
            if (starting) {
                return;
            }
        }

        new Thread(() -> {
            while (true) {
                log.debug("monitor....");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "monitor").start();
        starting = true;


    }

}



