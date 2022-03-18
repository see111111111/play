package com.lyl.play.demo.chat;

public class Test01 {
    public static void main(String[] args) {

        Test01 test01 = new Test01();
        test01.f5();
       /* long max = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("max="+max+"字节\t"+(max/(double)1024/1024)+"MB");
        System.out.println("totalMemory="+totalMemory+"字节\t"+(totalMemory/(double)1024/1024)+"MB");*/
    }

    public void f5(){
        int i=0;
        int j=0;
        if (false && (i++ == 0));
        if (false || (j++ == 0));
        System.out.println(i);
        System.out.println(j);
    }

}
