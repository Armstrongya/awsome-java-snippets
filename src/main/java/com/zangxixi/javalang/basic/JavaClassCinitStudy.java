package com.zangxixi.javalang.basic;

/**
 * 类的初始化和类加载还有些不同
 * Created by zxx on 2017/8/24 下午10:37.
 */
public class JavaClassCinitStudy {

    //可以查看不同类库的 loadClass实现
    ClassLoader classLoader = new ClassLoader() {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);//可以点击进去, 查看各个开源库实现的 loadClass方法
        }
    };

    static class Parent {
        static {
            A = 2;//可以先赋值, 再声明, 但是不能在声明之前使用
            //System.out.println("A= " + A);//这里会报错
        }

        public static int A = 1;
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
