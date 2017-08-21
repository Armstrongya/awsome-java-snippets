package com.zangxixi.javalang.concurrency;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Exchanger用于两个线程之间的数据交换
 * Created by zxx on 2017/3/20 下午10:33.
 */
public class ExchangerStudy {
    Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
}
