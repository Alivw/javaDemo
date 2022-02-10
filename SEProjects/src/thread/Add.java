package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @Author jalivv
 * @Date 2022/1/7 20:39
 */
public class Add {

    public static long ans1;
    public static long ans2;
    public static long ans3;
    public static long ans4;
    public static long ans5;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Long> task = new FutureTask<Long>(() -> {
            System.out.println("task....");
            long sum = 0;
            for (int i = 0; i < 2000; i++) {
                sum += i;
            }
            return sum;
        });


        FutureTask<Long> task1 = new FutureTask<Long>(() -> {
            long sum = 0;
            for (int i = 2000; i < 4000; i++) {
                sum += i;
            }
            return sum;
        });

        FutureTask<Long> task2 = new FutureTask<Long>(() -> {
            long sum = 0;
            for (int i = 4000; i < 8000; i++) {
                sum += i;
            }
            return sum;
        });

        FutureTask<Long> task3 = new FutureTask<Long>(() -> {
            long sum = 0;
            for (int i = 8000; i < 10000; i++) {
                sum += i;
            }
            return sum;
        });

        ans1 = task1.get();
        ans2 = task2.get();
        ans3 = task3.get();
        ans4 = task.get();
        System.out.println(ans1 + ans2 + ans3 + ans4);

    }


}
