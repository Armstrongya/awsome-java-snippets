package com.zangxixi.javalang.concurrency;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 主要适用于线程间的等待, 某个线程等到其他多个线程执行完之后再执行, 不能重复使用, 源码的备注里提供了 2 个示例代码
 * 适用场景: 比如系统启动前可以开启多个线程进行检查, 都检查完毕之后再继续执行主线程的相关操作
 * @author zxx
 * @since 2016/5/26 20:25
 */
public class CountDownLatchStudy {

    @Test
    public void testCountDownLatch() {
        int c = 5;
        CountDownLatch countDownLatch = new CountDownLatch(c);
        for (int i = 0; i < c; i++) {
            new WorkerThread(countDownLatch).start(); //也可以换成线程池ExcutorService 来启动线程
        }

        try {
            System.out.println("等待子线程执行完毕....");

            countDownLatch.await();//等待其他线程执行完

            System.out.println("子线程们都已经执行完毕, 继续执行主线程的其他操作");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    /** 子线程 **/
    private class WorkerThread extends Thread {
        private final CountDownLatch countDownLatch;

        public WorkerThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            System.out.println(String.format("子线程 %s 开始执行", Thread.currentThread().getName()));
            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("子线程 %s 执行完毕", Thread.currentThread().getName()));

            countDownLatch.countDown();//执行完进行减一操作
        }
    }
}
