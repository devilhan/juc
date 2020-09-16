/**
 * synchronized关键字
 * 对某个对象加锁
 * @Author Han
 */

package com.devil.han.juc.c_004;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(FineCoarseLock.class)  -- 类锁
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		synchronized(T.class) {  //考虑一下这里写synchronized(this)是否可以？
			count --;
		}
	}

}
