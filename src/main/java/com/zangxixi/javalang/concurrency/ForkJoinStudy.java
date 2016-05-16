package com.zangxixi.javalang.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @since 2016/5/12 17:02
 */
public class ForkJoinStudy {
    public class CountTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 50;
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            boolean canCompute = (end - start) <= THRESHOLD;//自己设置的一个阈值
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                System.out.println("fork sub task");
                //将大任务分拆成小任务
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                //执行子任务
                leftTask.fork();
                rightTask.fork();

                int leftResult = leftTask.join();
                int rightResult = rightTask.join();
                //合并结果
                sum = leftResult + rightResult;
            }

            return sum;
        }
    }

    @Test
    public void testForkAndJoin() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //计算1+2+3+ ... + 1000000
        CountTask task = new CountTask(1, 1000000);
        //提交任务
        long fjbegin = System.currentTimeMillis();
        Future<Integer> result = forkJoinPool.submit(task);
        try {

            System.out.println(result.get() + "\tcost= " +  (System.currentTimeMillis() - fjbegin));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        int m = 0;
        long forbegin = System.currentTimeMillis();
        for(int i=0; i<1000000; i++) {
            m +=i;
        }
        System.out.println(m + "\tcost= " + String.valueOf(System.currentTimeMillis() - forbegin));

    }
}
