package com.zangxixi.javalang.concurrency;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier是一个可以重复使用的线程屏障, 每个线程执行完任务后调用await()等待其他线程执行完毕, 当所有线程都到达屏障之后再放行进行后续的操作
 * 相比于CountDownLatch, 它的设置更灵活, 除了设置线程的个数, 还能在多个线程都到达屏障后执行自定义的Runnable任务, 还可以设置await的超时时间
 * 可以参考这篇文章 http://www.cnblogs.com/dolphin0520/p/3920397.html
 * @author zxx
 * @since 2016/5/26 20:56
 */
public class CyclicBarrierStudy {

    /** 最基本的用法, 等待多个线程都到达屏障之后再执行后续的操作 **/
    @Test
    public void testCyclicBarrier() {
        int c = 3;
        CyclicBarrier barrier = new CyclicBarrier(c);//3个线程
        for (int i = 0; i < c; i++) {
            new WorkerThread(barrier).start();
        }

        System.out.println("主线程");
    }

    /** 等待多个线程都到达屏障后, 要执行一个Runnable线程, 然后再执行后续的操作 **/
    @Test
    public void testCyclicBarrierWithRunnable() {
        int c = 3;
        CyclicBarrier barrier = new CyclicBarrier(c, new SummaryRunnable());//在所有线程都到达屏障后, 先执行收尾的Runnable线程, 然后再执行后续操作
        for (int i = 0; i < c; i++) {
            new WorkerThread(barrier).start();
        }

        System.out.println("主线程");
    }

    /** CyclicBarrier的重复使用 **/
    @Test
    public void testCyclicBarrierRepeatable() {
        int c = 3;
        CyclicBarrier barrier = new CyclicBarrier(c, new SummaryRunnable());//在所有线程都到达屏障后, 先执行收尾的Runnable线程, 然后再执行后续操作
        for (int i = 0; i < c; i++) {
            new WorkerThread(barrier).start();
        }

        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nCyclicBarrier 重复使用啦啦啦\n");

        for (int i = 0; i < c; i++) {
            new WorkerThread(barrier).start();
        }

        System.out.println("主线程");
    }



    /** 子线程 **/
    private class WorkerThread extends Thread {
        private final CyclicBarrier cyclicBarrier;

        public WorkerThread(CyclicBarrier barrier) {
            this.cyclicBarrier = barrier;
        }

        public void run() {
            System.out.println(String.format("子线程 %s 开始执行", Thread.currentThread().getName()));

            System.out.println(String.format("子线程 %s 执行完毕, 等待其他线程执行完毕", Thread.currentThread().getName()));

            try {
                cyclicBarrier.await();//开始等待, 这里也可以显式的设定超时时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("所有子线程都执行完毕了, 继续后续的操作");
        }
    }

    /** 收尾的线程 **/
    private class SummaryRunnable implements Runnable{
        public void run() {
            System.out.println("很好, 你们这些子线程都执行完毕了, 我该出场收尾了");
        }
    }

}
