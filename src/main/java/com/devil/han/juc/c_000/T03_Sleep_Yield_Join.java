package com.devil.han.juc.c_000;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    static void testSleep() {  //让给别的线程去执行
        new Thread(()->{
           common();
        }).start();
    }

    static void testYield() {   //当前线程返回到就绪状态
        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                if(i%10 == 0) Thread.yield();


            }
        }).start();

        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("------------B" + i);
                if(i%10 == 0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {  //将其他线程添加到执行状态，当前线程就绪
        Thread t1 = new Thread(()->{
            for (int i=0;i<100;i++){
                System.out.println("------------B"+i);
            }
        });

        Thread t2 = new Thread(()->{

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           common();
        });

        t1.start();
        t2.start();
    }

    private static void  common(){
        for(int i=0; i<100; i++) {
            System.out.println("A" + i);
            try {
                Thread.sleep(500);
                //TimeUnit.Milliseconds.sleep(500)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
