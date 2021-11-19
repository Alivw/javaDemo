package 数列求和;

/**
 * @Author: Alivv
 * @Create: 2021-11-19 09:35
 **/
public class Main {
    public static void main(String[] args) {

        /**
         *
         * 2/1,3/2,5/3,8/5,13/8....
         *
         */
        double sum = 0;
        for (int i = 0; i < 20; i++) {
            sum += G(i + 1);
        }

        System.out.println("sum = " + sum);

        sum = 0;
        double f=2;
        double s = 1;
        for (int i = 0; i < 20; i++) {
            sum += f / s;
            double temp = f;
            f = f + s;
            s = temp;
        }
        System.out.println("sum = " + sum);
    }

    private static double G(int n) {
        if (n == 2) return 3 / 2.0;
        if(n==1) return 2;
        return 1 + f(n - 2) / f(n - 1);
    }

    private static double f(int n) {
        if(n<=2) return n + 1;
        return f(n - 1) + f(n - 2);
    }
}
