package com.zangxixi;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        String m = "";
        int x = m.toCharArray().length;
        System.out.println(m.length());
        LinkedHashMap hashMap = new LinkedHashMap();
        LinkedList<Integer> ll = new LinkedList<Integer>();
        Stack<String> stack = new Stack<String>();


        long beginTs = System.currentTimeMillis();
        long result = fib(50);
        long endTs = System.currentTimeMillis();
        System.out.println("result= " + result + "\tcosts= " + (endTs - beginTs));
    }

    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
