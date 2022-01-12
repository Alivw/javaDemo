package thread;

/**
 * @Description
 * @Author jalivv
 * @Date 2022/1/7 21:07
 */
public class Add2 {

    static Long a1 = 0l;
    static Long a2 = 0l;
    static Long a3 = 0l;
    static Long a4 = 0l;
    static Long a5 = 0l;

    public static void main1(String[] args) throws InterruptedException {

        int[] arr = new int[20000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Thread tt = new Thread(() -> {
            Thread t1 = new Thread(() -> {
                long res = 0l;
                for (int i = 0; i < 2000; i++) {
                    res += arr[i];
                }
                a1 = res;
            });
            t1.start();
            //try {
            //    t1.join();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}


            Thread t2 = new Thread(() -> {
                long res = 0l;
                for (int i = 2000; i < 4000; i++) {
                    res += arr[i];
                }
                a2 = res;
            });
            t2.start();
            //try {
            //    t2.join();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}

            Thread t3 = new Thread(() -> {
                long res = 0l;
                for (int i = 4000; i < 6000; i++) {
                    res += arr[i];
                }
                a3 = res;
            });
            t3.start();
            //try {
            //    t3.join();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            //////////////////////////////
            Thread t4 = new Thread(() -> {
                long res = 0l;
                for (int i = 6000; i < 8000; i++) {
                    res += arr[i];
                }
                a4 = res;
            });
            t4.start();
            //try {
            //    t4.join();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            ////////////////////////////
            Thread t5 = new Thread(() -> {
                long res = 0l;
                for (int i = 8000; i < 10000; i++) {
                    res += arr[i];
                }
                a5 = res;
            });
            t5.start();
            //try {
            //    t5.join();
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            try {
                t5.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        tt.start();
        tt.join();
        System.out.println(a1 + a2 + a3 + a4 + a5);
    }

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (a1) {
                System.out.println("获取到a1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(() -> {
            synchronized (a2) {
                System.out.println("获取到a2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
