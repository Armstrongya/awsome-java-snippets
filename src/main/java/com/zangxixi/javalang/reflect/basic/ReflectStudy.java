package com.zangxixi.javalang.reflect.basic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zxx on 2017/10/10 上午7:45.
 */
public class ReflectStudy {

    public static void main(String[] args) {
        showClassApi();

    }

    public static void showClassApi() {
        Class cls = DriveCar.class;
        print("className= " + cls.getName() + "\tcanonicalName= " + cls.getCanonicalName());

        print("\n");
        Field[] fields = cls.getDeclaredFields();//
        for (Field field : fields) {
            print("field= " + field.getName() + "\tmodifier= " + field.getModifiers() + "\tgenericType= " + field.getGenericType());
        }

        print("\n");
        Method[] methods = cls.getDeclaredMethods();//使用getMethods()方法会把Object类默认的方法都列出来
        for (Method method : methods) {
            print("method= " + method.getName() + "\tmodifier= " + method.getModifiers());
        }

    }

    private static void print(Object o) {
        System.out.println(String.valueOf(o));
    }
}
