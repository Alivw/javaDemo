package thread.juc.demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface share {
    void incr() throws InterruptedException;

    void desc() throws InterruptedException;
}

/**
 * @Description 尚硅谷线程间通信
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/1 13:29
 */
public class Main {
    public static void main(String[] args) {

        //share share = new shareIf();
        //share share = new shareWhile();
        share share = new shareLock();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}

class shareWhile implements share {

    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        while (number != 0) {
            wait();
        }

        number++;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "::" + number);
    }

    public synchronized void desc() throws InterruptedException {
        while (number == 0) {
            wait();
        }

        number--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "::" + number);
    }

}

class shareIf implements share {

    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        if (number != 0) {
            wait();
        }

        number++;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "::" + number);
    }

    public synchronized void desc() throws InterruptedException {
        if (number == 0) {
            wait();
        }

        number--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "::" + number);
    }

}

class shareLock implements share {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private int number = 0;

    @Override
    public void incr() throws InterruptedException {
        try {
            lock.lock();
            while (number != 0) {
                condition.await();
            }
            number++;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "::" + number);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void desc() throws InterruptedException {
        try {
            lock.lock();
            while (number != 1) {
                condition.await();
            }
            number--;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "::" + number);
        } finally {
            lock.unlock();
        }
    }
}
