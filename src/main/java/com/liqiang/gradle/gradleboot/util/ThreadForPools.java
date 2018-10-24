package com.liqiang.gradle.gradleboot.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadForPools implements Runnable {

    private int index;

    public ThreadForPools(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        log.debug("开始处理第{}个线程", index);
        try {
            Thread.sleep(1000);
            log.debug("我的线程标识是：{}", this.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
