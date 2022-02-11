package com.jalivv.maven.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
//import static com.rh.bilibili.utils.Sleeper.sleep;

/**
 * @Description:
 * @author: Jalivv
 * @date: 2022/2/11 09:26
 */
@Slf4j(topic = "c.Test")
public class Test {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(() -> {
            lock.lock();
            log.debug("locked...");
            try {
                lock.lock();
                try {
                    log.debug("locking...");
                }finally {
                    lock.unlock();
                }


                log.debug("locking...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }

        }, "t1").start();


        new Thread(() -> {
            lock.lock();
            try {
                log.debug("locking...");
            } finally {
                log.debug("unlocking...");
                lock.unlock();
            }

        }, "t2").start();
    }
}

/**
 * 自定义锁 （不可重入锁）
 */
class MyLock implements Lock {

    // 独占锁
    class MySync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                // 加上锁,并且设置 owner 为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 线程是否持有独占锁 只需要判断 state 是否为1
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();

    // 加锁 不成功会放入队列等待
    @Override
    public void lock() {
        sync.acquire(1);
    }

    // 加锁 可打断的
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    //  尝试加锁 一次不成功 就会返回false 不会死等
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    // 尝试加锁 带超时时间
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    // 解锁
    @Override
    public void unlock() {
        /**
         * release 方法，会调用 tryRelease() ，释放锁，并且唤醒等待线程(tryRelease 没有的功能)
         */
        sync.release(1);
    }

    // 条件变量
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

}

