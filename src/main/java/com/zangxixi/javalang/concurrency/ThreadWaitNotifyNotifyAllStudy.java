package com.zangxixi.javalang.concurrency;

import java.util.HashMap;

/**
 * @author 张新星 zhangxinxing@xiaomi.com on
 * @since 2017/3/8 18:29
 */
public class ThreadWaitNotifyNotifyAllStudy {
    public static void main(String[] args) {
        Object a = new Object();
        try {
            synchronized(a) {
                a.wait();
                a.notify();
            }
            //在不加锁的情况下直接执行wait()或notify()方法会产生IllegalMonitorStateException, 因为线程竞争状态是不确定的
            /*a.wait();
            a.notify();*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap hashMap = new HashMap();
    }
}
