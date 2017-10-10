package com.zangxixi.javalang.reflect.sweetshop;

/**
 * 主要是展示 Class.forName("") 在生成 class对象引用时的类加载能力
 * static静态代码块在类加载的时候执行的
 *
 * Created by zxx on 2017/10/9 上午8:02.
 */
public class SweetShop {
    public static void main(String[] args) {
        System.out.println("inside main");
        new  Candy();
        System.out.println("after creating Candy\n");

        /** 这里类加载时执行了类的静态代码块, 但是只有在生成实例的时候才会触发类构造器 **/
        try {
            Class.forName("com.zangxixi.javalang.reflect.sweetshop.Gum");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("after Class.forName(\"Gum\")\n");

        new Cookie();
        System.out.println("after creating Cookie");
    }
}
