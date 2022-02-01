package thread.juc.demo1;

import javax.sound.midi.Soundbank;

/**
 * @Description 小朋友 冰淇淋
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/1 11:25
 */
public class Main {

    public static final Object ICE_CREAM = null;

    public static void main(String[] args) throws InterruptedException {


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
}
