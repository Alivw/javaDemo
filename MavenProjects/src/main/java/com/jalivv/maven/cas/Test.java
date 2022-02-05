package com.jalivv.maven.cas;

import java.awt.image.BandCombineOp;
import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

interface Account {
    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withdraw(10);

            }));
        }

        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();

        System.out.println(account.get() + " cost" + (end - start) / 1000_000 + "ms");
    }

    int get();

    void withdraw(int amount);
}

/**
 * @Description
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/4 15:44
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Account.demo(new AccountUnsafe(10000));
        }
        System.out.println("..................");
        for (int i = 0; i < 10; i++) {
            Account.demo(new AccountCAS(10000));
        }
    }
}

class AccountUnsafe implements Account {
    private int balance;

    public AccountUnsafe(int balance) {
        this.balance = balance;
    }

    @Override
    public int get() {
        return balance;
    }

    @Override
    public void withdraw(int amount) {
        balance -= amount;
    }
}

class AccountCAS implements Account {

    private AtomicInteger balance;

    public AccountCAS(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public int get() {
        return balance.get();
    }

    //@Override
    //public void withdraw(int amount) {
    //    int prev, next;
    //    do {
    //        prev = balance.get();
    //        next = prev - amount;
    //    } while (!balance.compareAndSet(prev, next));
    //}


    @Override
    public void withdraw(int amount) {
        balance.updateAndGet(v -> v - amount);
    }
}
