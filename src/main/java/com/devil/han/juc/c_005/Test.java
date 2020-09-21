package com.devil.han.juc.c_005;

/**
 * @author Han
 * @date 2020/9/16
 */
public class Test implements Runnable  {
    private /*volatile*/ int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"count = "+count);
    }

    public static void main(String[] args) {
        Test test = new Test();
        for (int i=0;i<100;i++){
            new Thread(test, "thread"+i).start();
        }
    }
}
