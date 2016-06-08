package com.zangxixi.javalang.concurrency;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 【其实还是没有很好的理解和掌握, 尴尬, @TODO: 后续有了真实的使用场景再回来看看】
 * 读写锁, 适用于读多写少的场景, 只能有一个写锁, 读取的时候可以有多个读锁
 * 有一些高级的模式, 比如降级, 支持一个持有写锁的线程再拿一个读锁, 反之则不行, 此外还有一些其他模式, 参考 ReentrantReadWriteLock 的源码, 有2个好例子
 *
 * 参考文章 http://ifeve.com/read-write-locks/
 *
 * @author zxx
 * @since 2016/6/2 21:18
 */
public class ReadWriteLockStudy {

    /** 这段代码用这种方式执行有些问题, 每次都不一样, 有时候什么结果都没有 **/
    @Test
    public void testReadWriteLock() {
        RWDictionary rwDictionary = new RWDictionary();
        rwDictionary.put("111", 111);
        rwDictionary.get("111");
        for (int i = 0; i< 2; i++) {
            new Thread(new writeRunnable(rwDictionary)).start();
            new Thread(new readRunnable(rwDictionary)).start();
        }
    }

    private class RWDictionary {
        private final Map<String, Integer> map = new TreeMap<String, Integer>();
        private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();//可重入的读写锁
        private final Lock r = rwl.readLock();
        private final Lock w = rwl.writeLock();

        public Integer get(String key) {
            r.lock();
            try{
                return map.get(key);
            }finally {
                System.out.println("before return, execute this r.unlock");
                r.unlock();
            }
        }

        public Object[] allKeys() {
            r.lock();
            try{
                return map.keySet().toArray();
            }finally {
                r.unlock();
            }
        }

        public Integer put(String key, Integer value) {
            w.lock();
            try {
                return map.put(key, value);
            } finally {
                w.unlock();
            }
        }

        public void clear() {
            w.lock();
            try {
                map.clear();
            } finally {
                w.unlock();
            }
        }
    }

    private class writeRunnable implements Runnable {
        private final RWDictionary rwDictionary;
        private writeRunnable(RWDictionary rwDictionary) {
            this.rwDictionary = rwDictionary;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                String key = String.valueOf(RandomStringUtils.randomAlphanumeric(5));
                rwDictionary.put(key, i);
                System.out.println(String.format("%s 写入了kv: %s", Thread.currentThread().getName(), i + "\t" + key));
            }
        }
    }

    private class readRunnable implements Runnable {
        private final RWDictionary rwDictionary;
        private readRunnable(RWDictionary rwDictionary) {
            this.rwDictionary = rwDictionary;
        }

        public void run() {
            for (int i = 0; i < 1; i++) {
                Object[] keys = rwDictionary.allKeys();
                System.out.println(String.format("%s 读取了all keys: %s", Thread.currentThread().getName(), StringUtils.join(keys, ", ")));
            }
        }
    }
}
