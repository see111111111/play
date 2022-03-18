package com.lyl.play.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test01 {

    public static void test1(){
        User u=new User();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            u.getName();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start+"ms");
    }

    public static void test2() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u=new User();
        Class<? extends  User> aClass = u.getClass();
        Method getName = aClass.getDeclaredMethod("getName", null);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(u,null);
        }
        long end = System.currentTimeMillis();
        System.out.println("p1"+(end-start)+"ms");
    }
    public static void test3() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u=new User();
        Class<? extends User> aClass = u.getClass();
        Method getName = aClass.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(u,null);
        }
        long end = System.currentTimeMillis();
        System.out.println("p2"+(end-start)+"ms");
    }



    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        test1();
        test2();
        test3();


        Class aClass = Class.forName("com.lyl.play.demo.User");
        Field address = aClass.getField("address");
        //System.out.println(address);
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
           // System.out.println(field);
        }
        fields=aClass.getDeclaredFields();
        for (Field field : fields) {
            //System.out.println(field);
        }
        //System.out.println(aClass.getDeclaredField("name"));

        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            //System.out.println(method);
        }
        methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            //System.out.println("========"+method);
        }
        //System.out.println(aClass.getMethod("aaa", String.class));
        //System.out.println(aClass.getDeclaredMethod("aaa", String.class));


    }

}
