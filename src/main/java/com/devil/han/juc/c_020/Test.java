package com.devil.han.juc.c_020;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Hanyanjiao
 * @date 2020/9/18
 */
public class Test {

    private static volatile int count;

    public static void main(String[] args){

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                count++;
                System.out.println(count);
                if (i==4){
                    LockSupport.park();
                }
            }
        });

        Thread t2 = new Thread(()->{
            if (count==5){
                System.out.println("t2 结束");
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();

    }
}
