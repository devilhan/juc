package com.devil.han.juc.c_007;

/**
 * @author Han
 * @date 2020/9/16
 */
public class Test {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"  m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end...");
    }

    public void m2(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2...");
    }

    public static void main(String[] args) {
        Test test = new Test();
//        new Thread(test::m1,"t1").start();
//        new Thread(test::m2,"t2").start();
        new Thread(()->test.m1(),"m1").start();
        new Thread(()->test.m2(),"m2").start();
    }

}
