package thread.juc.countdownlanch;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {


        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        String[] all = new String[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int j = i;
            pool.execute(()->{
                for (int k = 0; k <= 100; k++) {
                    try {
                        Thread.sleep(random.nextInt(150));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    all[j] = k + "%";
                    System.out.print("\r" + Arrays.toString(all));
                }
                countDownLatch.countDown();
            });

        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n 游戏开始");

    }
}
