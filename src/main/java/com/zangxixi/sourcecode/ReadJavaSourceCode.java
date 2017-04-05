package com.zangxixi.sourcecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 在这里做一个备注, 标记一下我看过的 JDK 或其他 第三方类库的源码
 * @author zxx
 * @since 2016/4/26 16:35
 */
public class ReadJavaSourceCode {

    //集合类源码
    public void readJdkCollectionCode() {
        /** cow 适用于读多写少的场景, 用 ReentrantLock 来加解锁, 线程安全 **/
        List<Integer> cowList = new CopyOnWriteArrayList<Integer>();//done, 2016-04-26

        /** 收获蛮大的, 扩容是1.5倍增长, 用modCount来为structural modification计数, 不一致就抛并发修改错误异常 **/
        List<Integer> arryList = new ArrayList<Integer>();//done, 2016-04-26

        /** 代码挺流畅的, 复用很多子方法, 简洁明了 **/
        List<Integer> linkedList = new LinkedList<Integer>();//done, 2016-04-27

        /** 基于 HashMap 实现的 **/
        Set<Integer> hashSet = new HashSet<Integer>();//done, 2016-04-27

        /** 基于 TreeMap 实现的 **/
        Set<Integer> treeSet = new TreeSet<Integer>();//done, 2016-04-27

        /** 基于 LinkedHashMap 实现的, 用了父类HashSet的一个构造器 **/
        Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();//done, 2016-04-27

        /** 基于CopyOnWriteArrayList, 线程安全 **/
        Set<Integer> cowArraySet = new CopyOnWriteArraySet<Integer>();//done, 2016-04-27

        /** 基于数组和单向链表实现的, 代码看着挺不错的 **/
        Map<String, Integer> hashMap = new HashMap<String, Integer>();

        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();

        /** 基于数组和双向链表实现的, 跟HashMap的存储结构很像, 不过Entry都是双向链表, 插入的元素按顺序连起来了 **/
        Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();

        /** treeMap 用的红黑树来维护排序 **/
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();//half done

        /** 跳表实现的 **/
        ConcurrentSkipListMap<String, Integer> skipMap = new ConcurrentSkipListMap<String, Integer>();
    }

    //JUC并发包源码
    public void readJdkConcurrencyCode() {
        /** AbstractQueuedSynchronizer 这个框架木有看懂, 看了网上的解读文章才有了一些认识, @TODO 后面要再过头来看一遍
         * http://www.infoq.com/cn/articles/jdk1.8-abstractqueuedsynchronizer 很赞的两篇分析文章
         * http://www.infoq.com/cn/articles/java8-abstractqueuedsynchronizer
         * **/
        class aqs extends AbstractQueuedSynchronizer {}

        /** 线程池的参数配置参考这篇文章, <a href="http://www.infoq.com/cn/articles/java-threadPool">JAVA线程池的分析和使用</a>
         * 不同线程池实现的一个重要区别就是任务阻塞队列的选型, ArrayBlockingQueue, LinkedBlockingQueue, SynchronousQueue, PriorityBlockingQueue
         * 性质不同的任务可以用不同规模的线程池分开处理:
         * 1) CPU密集型任务配置尽可能小的线程，如配置 Ncpu+1个线程的线程池;
         * 2) IO密集型任务则由于线程并不是一直在执行任务，则配置尽可能多的线程，如 2*Ncpu;
         * 3) 混合型的任务，如果可以拆分，则将其拆分成一个CPU密集型任务和一个IO密集型任务**/

        /** 像线程的缓存池一样, 适用于大量短时的任务, 无界能自动回收, 有可复用的线程就继续复用, 没有就创建新的线程, 线程idle 超过60秒就kill掉 **/
        ExecutorService cachedThreadPoolService = Executors.newCachedThreadPool();

        /** 确保最多只有一个线程在执行, 线程出现异常后会新创建一个 **/
        ExecutorService singleThreadservice = Executors.newSingleThreadExecutor();

        /** 固定大小的线程池 **/
        ExecutorService fixedThreadPoolService = Executors.newFixedThreadPool(3);

        /** 支持任务定时调度的线程池 **/
        ExecutorService scheduledThreadPoolService = Executors.newScheduledThreadPool(3);

        /** CompletionService和ExecutorService的最大区别是前者按照线程完成任务的先后顺序来获取结果的, 先完成的先取出;
         * 不像后者按提交任务顺序获取结果, 如果有一个任务未执行完, 哪怕后面有其他任务完成了也得等待, 阻塞时间相对较长
         * 用QueueingFuture包装了一下future task, 每个任务执行完毕就会添加到 BlockingQueue 中**/
        CompletionService<String> completionService = new ExecutorCompletionService<String>(fixedThreadPoolService);//把正常的线程池又包了一层

        /** 阻塞队列 BlockingQueue 可以参考这篇文章 <a href="http://www.infoq.com/cn/articles/java-blocking-queue">Java中的阻塞队列</a> **/
    }


}
