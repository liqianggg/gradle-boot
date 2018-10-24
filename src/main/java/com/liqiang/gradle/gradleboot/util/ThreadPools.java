package com.liqiang.gradle.gradleboot.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPools {
    public static void main(String[] args) {
//       newSingleThreadExecutor();
//        newCachedThreadPool();
//        newFixedThreadPool();
//        newScheduledThreadPool();
        threadPoolExecutor();
//        final int add = add(1, 2);
//        System.out.println(add);

    }


    public static void newSingleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        log.debug("******************newSingleTgreadExecutor**************");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new ThreadForPools(i));
        }
        executorService.shutdown();
    }

    public static void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        log.debug("******************newSingleTgreadExecutor**************");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new ThreadForPools(i));
        }
        executorService.shutdown();
    }

    public static void newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        log.debug("******************newSingleTgreadExecutor**************");
        for (int i = 0; i < 4; i++) {
            executorService.execute(new ThreadForPools(i));
        }
        executorService.shutdown();
    }

    public static void newScheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        log.debug("******************newSingleTgreadExecutor**************");
        for (int i = 0; i < 4; i++) {
            executorService.schedule(new ThreadForPools(i), 3, TimeUnit.SECONDS);
        }
        executorService.shutdown();
    }

    public static void threadPoolExecutor() {

        int corePoolSize = 3;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        for (int i = 0; i < 10; i++) {
            executor.execute(new ThreadForPools(i));
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();

    }

    public static int add(int a, int b) {
        if (b == 0)
            return a;
        int t = a ^ b;
        int ca = (a & b) << 1;
        return add(t, ca);
    }
}
