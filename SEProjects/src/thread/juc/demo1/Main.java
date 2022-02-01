package thread.juc.demo1;

/**
 * @Description 小朋友 冰淇淋
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/1 11:25
 */
public class Main {

    public static final Object ICE_CREAM = null;

    public static void main(String[] args) throws InterruptedException {
        //new Main().test1();
        //new Main().test2();
        //new Main().test3();
        new Main().test4();


    }

    public void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            if (ICE_CREAM == null) {
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
}
