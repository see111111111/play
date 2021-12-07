package com.lyl.play.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPollExecutorTest {
    private static final int taskCount = 5;

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                10,20,5,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(30)
        );
        System.out.println("总任务数："+taskCount);
        long start = System.currentTimeMillis();
        for (int i = 0; i < taskCount; i++) {
            Thread thread=new Thread(() -> {
                try {
                    Thread.sleep(500);//模拟执行耗时
                    System.out.println("已执行" + atomicInteger.addAndGet(1) + "个任务");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            try {
                //注意这里我try起来了，默认拒绝策略会报错
                threadPoolExecutor.execute(thread);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        long end = 0;
        while (threadPoolExecutor.getCompletedTaskCount()<50){
            end= System.currentTimeMillis();
        }
        System.out.println("任务总耗时："+(end-start));
    }

}
