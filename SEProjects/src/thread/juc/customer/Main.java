package thread.juc.customer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/1 14:35
 */
public class Main {

    public static void main(String[] args) {
        shareCustom share = new shareCustom();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    share.print2(i + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    share.print3(i + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    share.print5(i + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();


    }
}


class shareCustom {

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    private int flag = 1;

    public void print2(int loop) throws InterruptedException {
        try {
            lock.lock();
            while (flag != 1) {
                c1.await();
            }

            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + (i + 1) + ":轮数:" + loop);
            }
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print3(int loop) throws InterruptedException {
        try {
            lock.lock();
            while (flag != 2) {
                c2.await();
            }

            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + (i + 1) + ":轮数:" + loop);
            }
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print5(int loop) throws InterruptedException {
        try {
            lock.lock();
            while (flag != 3) {
                c3.await();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + (i + 1) + ":轮数:" + loop);
            }
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }


}
