package com.zangxixi.javalang.concurrency;

/**
 * 在线程 a 中调用 t.join()，会让线程 a 阻塞，直到线程 t 执行完毕之后才能唤醒线程 a
 * @author 张新星 zhangxinxing@xiaomi.com on
 * @since 2017/3/8 16:55
 */
public class ThreadJoinStudy {

    public static void main(String[] args) {
        MyRunnable m1 = new MyRunnable("m1");
        Thread t1 = new Thread(m1);
        MyRunnable m2 = new MyRunnable("m2");
        Thread t2 = new Thread(m2);

        //t1.join();
        t2.start();
        t1.start();

        try {
            t1.join();
            //t2.join();;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread finished");
    }

    private static class MyRunnable implements Runnable {
        private final String name;
        public MyRunnable(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + "; name= " + name + "; start");
            try {
                Thread.sleep(10000l);
                System.out.println(Thread.currentThread().getName() + "; name= " + name + "; finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
