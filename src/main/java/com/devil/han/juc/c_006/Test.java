package com.devil.han.juc.c_006;

/**
 * @author Han
 * @date 2020/9/16
 */
public class Test implements Runnable {
    private int count = 10;

    @Override
    public void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+",count = "+count);
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            Test test = new Test();
            new Thread(test,"thread"+i).start();
        }
    }
}
