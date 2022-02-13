package com.jalivv.maven.rwlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description: 读写锁
 * @author: Jalivv
 * @date: 2022/2/13 10:01
 */
@Slf4j(topic = "c.Test")
public class Test {

    public static void main(String[] args) {
        DataContainner containner = new DataContainner();
        new Thread(() -> {
            containner.read();
        }, "t1").start();
        new Thread(() -> {
            containner.read();
        }, "t1").start();
        new Thread(() -> {
            containner.read();
        }, "t1").start();


    }
}

@Slf4j(topic = "c.DataContainner")
class DataContainner {

    private Object data;

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        w.lock();
        log.debug("locking...");
        try {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("read");
            return data;
        } finally {
            log.debug("unlock");
            w.unlock();
        }
    }


    public void write(Object o) {
        w.lock();
        log.debug("locking...");
        try {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("write");

        } finally {
            log.debug("unlock");
            w.unlock();
        }
    }



}
