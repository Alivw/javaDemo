package thread;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Description
 * @Author jalivv
 * @Date 2022/1/7 20:39
 */
public class Add {


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        long time = new Date().getTime();
        System.out.println(forkJoinPool.invoke(new Work(10_0000)));
        System.out.println("耗时：" + (new Date().getTime()-time));
        time = new Date().getTime();
        long sum = 0;
        for (int i = 0; i <10_0000; i++) {
            sum += i;
        }
        System.out.println("耗时：" + (new Date().getTime()-time));
        System.out.println(sum);

    }
}

class Work extends RecursiveTask<Long> {
    int n;

    public Work(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 0) {
            return 0L;
        }
        Work work = new Work(n - 2_0000);
        Work work1 = new Work(n - 4_0000);
        Work work2 = new Work(n - 6_0000);
        Work work3 = new Work(n - 8_0000);
        Work work4 = new Work(n - 10_0000);
        work.fork();
        work1.fork();
        work2.fork();
        work3.fork();
        work4.fork();
        long sum = 0;
        for (int i = n - 2_0000; i < n; i++) {
            sum += i;
        }
        return sum + work.join()+work1.join()+work2.join()+work3.join()+work4.join();
    }
}
