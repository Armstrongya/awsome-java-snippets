package com.zangxixi.sourcecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在这里做一个备注, 标记一下我看过的 JDK 或其他 第三方类库的源码
 * @author zxx
 * @since 2016/4/26 16:35
 */
public class ReadJavaSourceCode {

    //集合类源码
    public void readJdkCollectionCode() {
        /** cow 适用于读多写少的场景, 用 ReentrantLock 来加解锁 **/
        List<Integer> cowList = new CopyOnWriteArrayList<Integer>();//done

        /** 收获蛮大的, 扩容是1.5倍增长, 用modCount来为structural modification计数, 不一致就抛并发修改错误异常 **/
        List<Integer> arryList = new ArrayList<Integer>();//done

        /** treeMap 用的红黑树来维护排序 **/
        Map<String, Integer> treeMap = new TreeMap<String, Integer>();//half done
    }

}
