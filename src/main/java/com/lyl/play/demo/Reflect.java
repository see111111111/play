package com.lyl.play.demo;

import lombok.Data;

import java.lang.annotation.Annotation;

public class Reflect {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("com.lyl.play.demo.Car");

        Annotation[] annotations = c1.getAnnotations();
        System.out.println(annotations);

    }
}

@Data
@MyAnnotation("qqq")
class Car{
    private String name;
    private int age;
    private int sex;
}
