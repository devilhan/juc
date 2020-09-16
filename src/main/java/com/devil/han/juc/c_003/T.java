/**
 * synchronized关键字
 * 对某个对象加锁
 * @Author Han
 */

package com.devil.han.juc.c_003;

public class T {

	private int count = 10;
	
	public synchronized void m() {  //等同于在方法的代码执行时要synchronized(this)  --对象锁、非静态方法
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}


}
