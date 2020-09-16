package com.devil.han.juc.c_000;

public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    public static void main(String[] args) {
        new MyThread().start(); //继承thread类
        new Thread(new MyRun()).start(); //实现Runnable接口
        new Thread(()->{            //使用Lambda方法
            System.out.println("Hello Lambda!");
        }).start();
    }

}

//请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThread (通过线程池来启动)