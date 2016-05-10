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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

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

        /** 基于数组和双向链表实现的, 跟HashMap的存储结构很像, 不过Entry都是双向链表, 插入的元素按顺序连起来了 **/
        Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();

        /** treeMap 用的红黑树来维护排序 **/
        Map<String, Integer> treeMap = new TreeMap<String, Integer>();//half done
    }

}
