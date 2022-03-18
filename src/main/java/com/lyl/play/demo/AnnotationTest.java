package com.lyl.play.demo;


import java.lang.annotation.*;

@MyAnnotation("sansan")
public class AnnotationTest {

    @MyAnnotation("qinqin")
    public void test1(){
    }

}

@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyAnnotation{
    String value();
    int i() default 0;
}
