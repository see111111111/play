package com.lyl.play.demo.down;

public class Test {
    public static void main(String[] args) {
        for (int i = 1; i <=9 ; i++) {
            for (int j = 1; j <=i ; j++) {
                System.out.print(j+"*"+i+"="+i*j+"\t");
            }
            System.out.println();
        }

        for (int i = 0; i <args.length ; i++) {
            System.out.println(args[i]);
        }
    }
}
