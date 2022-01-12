package thread;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author jalivv
 * @Date 2022/1/7 20:39
 */
public class Add {


    static Long a1 = 0l;
    static Long a2 = 0l;
    static Long a3 = 0l;
    static Long a4 = 0l;
    static Long a5 = 0l;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = new int[20000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Long curr = new Date().getTime();
        CompletableFuture<Long> res1 = CompletableFuture.supplyAsync(() -> {
            long res = 0l;
            for (int i = 0; i < 2000; i++) {
                res += arr[i];
            }
            return res;
        }, executor);
        CompletableFuture<Long> res2 = CompletableFuture.supplyAsync(() -> {
            long res = 0l;
            for (int i = 2000; i < 4000; i++) {
                res += arr[i];
            }
            return res;
        }, executor);
        CompletableFuture<Long> res3 = CompletableFuture.supplyAsync(() -> {
            long res = 0l;
            for (int i = 4000; i < 6000; i++) {
                res += arr[i];
            }
            return res;
        }, executor);
        CompletableFuture<Long> res4 = CompletableFuture.supplyAsync(() -> {
            long res = 0l;
            for (int i = 6000; i < 8000; i++) {
                res += arr[i];
            }
            return res;
        }, executor);
        CompletableFuture<Long> res5 = CompletableFuture.supplyAsync(() -> {
            long res = 0l;
            for (int i = 8000; i < 10000; i++) {
                res += arr[i];
            }
            return res;
        }, executor);


        a1 = res1.get();
        a2 = res2.get();
        a3 = res3.get();
        a4 = res4.get();
        a5 = res5.get();

        System.out.println("多线程耗时：" + (new Date().getTime() - curr));
        System.out.println(a1 + a2 + a3 + a4 + a5);


        curr = new Date().getTime();
        //long res = 0l;
        //for (int i = 0; i < arr.length; i++) {
        //    res += arr[i];
        //}
        //System.out.println("直接算耗时：" + (new Date().getTime() - curr));
        //System.out.println(res);


        new Thread(() -> {
            long res = 0l;
            for (int i = 0; i < 2000; i++) {
                res += arr[i];
            }
        }).start();

        new Thread(() -> {
            long res = 0l;
            for (int i = 2000; i < 4000; i++) {
                res += arr[i];
            }
        }).start();


        new Thread(() -> {
            long res = 0l;
            for (int i = 2000; i < 4000; i++) {
                res += arr[i];
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long res = 0l;
                for (int i = 6000; i < 8000; i++) {
                    res += arr[i];
                }
                a3 = res;
            }
        });


    }
}
