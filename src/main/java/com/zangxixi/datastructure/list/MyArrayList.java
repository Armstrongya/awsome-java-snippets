package com.zangxixi.datastructure.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 根据ArrayList的语义来重头实现一个，只实现了一部分api，还是有一些感觉的
 * Created by zxx on 16/4/10.
 */
public class MyArrayList<T> implements Iterable<T>{
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theList;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    //容量调整的
    private void ensureCapacity(int capacity) {
        if (capacity < theSize) {
            return;
        }

        T [] old = theList;
        theList = (T[]) new Object[capacity];
        for(int i = 0; i < size(); i++) {
            theList[i] = old[i];
        }
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0 ;
    }

    public T get(int i) {
        if (i < 0 || i >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return theList[i];
    }

    public T set(int i, T value) {
        if (i < 0 || i >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T old = theList[i];
        theList[i] = value;
        return old;
    }

    public void add(T t) {
        add(size(), t);
    }

    public void add(int idx, T t) {
        //TODO: 这里扩容有个大bug，究竟在什么情况下扩容呢?
        //TODO: 试着测试了下Java官方的add方法, add时指定的idx必须之前是有值的，不能直接跳过否则抛异常
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (theList.length == size()) {
            System.out.println("we expand capacity");
            ensureCapacity(size() * 2 + 1);
        }

        for(int i = theSize; i > idx; i++) {
            theList[i] = theList[i - 1];
        }

        theList[idx] = t;
        theSize++;
    }

    public T remove(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T old = theList[idx];
        for(int i = idx; i < size(); i++) {
            theList[i] = theList[i+1];
        }

        theSize--;

        return old;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        private int current = 0;

        public boolean hasNext() {
            return (current < size());
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return theList[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    @Test
    public void testMyArrayList() {
        MyArrayList<Integer> myArrayList = new MyArrayList<Integer>();
        myArrayList.add(10);
        myArrayList.add(9);
        System.out.println(myArrayList.get(0));
        Iterator<Integer> iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator= " + iterator.next());
        }

        List<Integer> testList = new ArrayList<Integer>(12);
        testList.add(5);
        testList.add(2, 7);//这里会抛异常，size = 1，而add 的index 是 2
        System.out.println(testList.get(1));
    }
}
