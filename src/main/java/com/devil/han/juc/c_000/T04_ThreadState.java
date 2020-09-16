package com.devil.han.juc.c_000;


/**
 * jvm(是在操作系统上运行)管理，线程状态分为:
 *  NEW
 *  RUNNABLE (READY/RUNNING)
 *  WAITING (TIME-WAITING/WAITING)
 *  BLOCKED
 *  TERMINATED
 *
 *
 *  终止线程：使用interrupt 捕获interruptedException,不在使用stop方法
 */
public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("guess : runnable ,answer ："+this.getState());

            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();

        System.out.println("guess : new,answer : "+t.getState());

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("guess : terminated ,answer : "+t.getState());

    }
}
