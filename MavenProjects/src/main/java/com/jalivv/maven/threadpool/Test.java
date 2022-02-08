package com.jalivv.maven.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockQueue<T> queue, T task);
}

/**
 * @Description 手写线程池
 * @Created: with IntelliJ IDEA.
 * @Author jalivv
 * @createTime 2022/2/8 10:15
 */
@Slf4j(topic = "c.Test")
public class Test {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 5, (queue, task) -> {

            if (queue.offer(task, 2, TimeUnit.SECONDS)) {
                log.debug("加入队列成功:{}", task);
            } else {
                log.debug("加入队列失败:{}", task);
            }
        });

        for (int i = 0; i < 20; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(2500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(j);
            });
        }
    }

}

@Slf4j(topic = "c.BlockQueue")
class BlockQueue<T> {
    /**
     * 任务队列
     */
    private Deque<T> queue = new ArrayDeque<>();

    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 生产者条件变量
     */
    private Condition fullWaitSet = lock.newCondition();

    /**
     * 消费者条件变量
     */
    private Condition emptyWaitSet = lock.newCondition();

    /**
     * 容量
     */
    private int capacity;

    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 带超时时间带阻塞等待
     *
     * @param timeout
     * @param timeUnit
     * @return
     */
    public T poll(long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);

            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                // 返回的是剩余的时间
                try {
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞获取
     *
     * @return
     */
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T t = queue.removeFirst();
            fullWaitSet.signalAll();
            return t;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 阻塞添加
     *
     * @param element
     */
    public void put(T element) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("任务等待加入队列....{}", element);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("任务加入任务队列{}", element);
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }


    /**
     * 带超时时间带阻塞添加
     *
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(task);
            emptyWaitSet.signalAll();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取容量
     *
     * @return
     */
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy rejectPolicy, T task) {
        lock.lock();
        try {
            // 判断队列是否已满
            if (queue.size() == capacity) {
                rejectPolicy.reject(this, task);
            } else {
                //  有空闲
                log.debug("加入任务队列:{}", task);
                queue.addLast(task);
                emptyWaitSet.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}

@Slf4j(topic = "c.ThradPool")
class ThreadPool {

    /**
     * 任务队列
     */
    private BlockQueue<Runnable> taskQueue;

    /**
     * 线程集合
     */
    private HashSet<Worker> workers = new HashSet<>();

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 获取任务的超时时间
     */
    private long timeout;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 拒绝策略
     */
    private RejectPolicy rejectPolicy;

    public ThreadPool(int corePoolSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy rejectPolicy) {
        this.taskQueue = new BlockQueue<>(queueCapacity);
        this.corePoolSize = corePoolSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    /**
     * 执行任务
     *
     * @param task
     */
    public void execute(Runnable task) {
        // 当任务数没有超过 corePoolSize 时，直接交给 worker 对象执行
        // 当任务数超过了 corePoolSize 时，就要加入任务队列暂存起来
        synchronized (workers) {
            if (workers.size() < corePoolSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker:{},task:{}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                //taskQueue.put(task);
                taskQueue.tryPut(rejectPolicy, task);
                // 死等
                // 带超时等待
                // 让调用者放弃执行
                // 让调用者抛出异常
                // 让调用者自己执行任务


            }
        }

    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        /**
         * 执行任务
         */
        @Override
        public void run() {
            // 当task 不为空，执行任务
            // task 为空，从taskQueue 取任务执行
            //while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(2000, TimeUnit.MILLISECONDS)) != null) {
                try {
                    log.debug("正在执行任务:{}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("移除线程:{}", this);
                workers.remove(this);
            }
        }
    }
}
