package thread.juc.demo1;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description 小朋友 冰淇淋
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/1 11:25
 */
public class Main {

    public static final Object ICE_CREAM = null;

    /**
     * 由于 jdk 底层遗留问题，可能挂起线程伪唤醒（没有 调用 notify、resume、unpark...方法，线程被唤醒了，称为伪唤醒）
     * 所以官方不建议用if判断 ，该用while
     */
    public static void main(String[] args) throws InterruptedException {
        //new Main().test1();
        //new Main().test2();
        //new Main().test3();
        //new Main().test4();
        //new Main().test5();
        new Main().test6();


    }

    public void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (ICE_CREAM == null) {
                System.out.println("没有冰淇淋，小朋友不开心，等待。。。");
                Thread.currentThread().suspend();
            }

            // 等待唤醒
            // 被唤醒就代表冰淇凌做好了
            System.out.println("小朋友买到冰淇凌");
        });
        t1.start();
        Thread.sleep(2000L);

        t1.resume();
        System.out.println("冰淇凌做好了，通知小朋友");
    }

    /**
     * 产生了死锁
     * 死锁的 suspend resume suspend不会像wait一样释放锁，故这样写容易出现死锁
     *
     * @throws InterruptedException
     */
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(this);
            if (ICE_CREAM == null) {
                System.out.println("没有冰淇淋，小朋友不开心，等待。。。");
                synchronized (this) {
                    Thread.currentThread().suspend();
                }
            }

            // 等待唤醒
            // 被唤醒就代表冰淇凌做好了
            System.out.println("小朋友买到冰淇凌");
        });
        t1.start();
        System.out.println(this);
        Thread.sleep(2000L);

        synchronized (this) {
            t1.resume();
        }
        System.out.println("冰淇凌做好了，通知小朋友");
    }


    /**
     * 导致程序永久挂起 suspend/resume
     * 小朋友 sleep 时间太长，由于 resume 的时候还没有执行  suspend ，造成程序永久挂起
     *
     * @throws InterruptedException
     */
    public void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            if (ICE_CREAM == null) {
                System.out.println("没有冰淇淋，小朋友不开心，等待。。。");
                try {
                    Thread.sleep(6000L);
                    Thread.currentThread().suspend();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

            // 等待唤醒
            // 被唤醒就代表冰淇凌做好了
            System.out.println("小朋友买到冰淇凌");
        });
        t1.start();
        Thread.sleep(2000L);
        t1.resume();
        System.out.println("冰淇凌做好了，通知小朋友");
    }

    /**
     * wait 方法会释放持有的对象锁
     * wait 方法必须放在同步代码块中，而且唤醒必须持有的是同一对象锁
     * 正常 wait/notify
     *
     * @throws InterruptedException
     */
    public void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            if (ICE_CREAM == null) {
                System.out.println("没有冰淇淋，小朋友不开心，等待。。。");
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

            // 等待唤醒
            // 被唤醒就代表冰淇凌做好了
            System.out.println("小朋友买到冰淇凌");
        });
        t1.start();
        Thread.sleep(2000L);
        synchronized (this) {
            this.notifyAll();
        }
        System.out.println("冰淇凌做好了，通知小朋友");
    }


    /**
     * wait/notify 导致程序永久挂起
     * notify 在前，wait 在后，导致程序永久挂起
     */
    public void test5() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            if (ICE_CREAM == null) {
                System.out.println("没有冰淇淋，小朋友不开心，等待。。。");
                try {
                    Thread.sleep(6000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

            // 等待唤醒
            // 被唤醒就代表冰淇凌做好了
            System.out.println("小朋友买到冰淇凌");
        });
        t1.start();
        Thread.sleep(2000L);
        synchronized (this) {
            this.notifyAll();
        }
        System.out.println("冰淇凌做好了，通知小朋友");
    }

    /**
     * park/unpark 解决程序永久挂起
     * 但是 park 并不会释放拥有的锁
     * 多次unpark 只会当作一次凭证来使用，如果 连续两次 park ，仍然会导致程序永久挂起
     */
    public void test6() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            if (ICE_CREAM == null) {
                System.out.println("没有冰淇淋，小朋友不开心，等待。。。");
                //try {
                //    Thread.sleep(6000L);
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //}
                synchronized (this) {
                    LockSupport.park();
                    //LockSupport.park();
                }


            }

            // 等待唤醒
            // 被唤醒就代表冰淇凌做好了
            System.out.println("小朋友买到冰淇凌");
        });
        t1.start();
        Thread.sleep(2000L);
        synchronized (this) {
            LockSupport.unpark(t1);
            //LockSupport.unpark(t1);
        }
        System.out.println("冰淇凌做好了，通知小朋友");
    }

}
