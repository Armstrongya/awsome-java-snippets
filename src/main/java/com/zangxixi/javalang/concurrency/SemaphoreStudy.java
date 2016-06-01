package com.zangxixi.javalang.concurrency;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量一般用于控制对某组资源的访问权限, 使用前要获得凭证, 用完后要释放凭证
 * 适用资源有限的场景, 比如有几十个线程想去访问数据库, 而数据库的连接数只有10,这个时候就需要Semaphore并发控制, 最多只能有10个线程拿到访问凭证
 * @author zxx
 * @since 2016/6/1 19:05
 */
public class SemaphoreStudy {

    /** 最基本的用法  **/
    @Test
    public void testSemaphore() {
        Semaphore semaphore = new Semaphore(3);//假设数据库最多只能有3个连接
        for (int i = 0; i < 6; i++) { //开了6个线程, 但是同时最多只能有3个线程能获得数据库连接
            new WorkerThread(semaphore).start();
        }
    }

    /** 子线程 **/
    private class WorkerThread extends Thread {
        private final Semaphore semaphore;
        public WorkerThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();//阻塞式的获得信号量, tryAcquire()是非阻塞式的
                System.out.println(String.format("线程 %s 拿到了数据库的连接", Thread.currentThread().getName()));
                System.out.println(String.format("线程 %s 释放了数据库的连接", Thread.currentThread().getName()));
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
