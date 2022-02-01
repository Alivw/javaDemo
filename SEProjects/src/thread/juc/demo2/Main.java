package thread.juc.demo2;

/**
 * @Description 尚硅谷线程间通信
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/1 13:29
 */
public class Main {
    public static void main(String[] args) {

        share share = new share();
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
    }
}


class share {

    private int number = 0;

    public synchronized void incr() throws InterruptedException {
        while (number == 1) {
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
