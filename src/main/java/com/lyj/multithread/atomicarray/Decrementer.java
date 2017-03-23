package com.lyj.multithread.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

//5.创建一个类，名为 Decrementer，并实现 Runnable 接口。
public class Decrementer implements Runnable {

	// 6.声明一个私有 AtomicIntegerArray 属性，名为 vector，用来储存一个整数 array。
	private AtomicIntegerArray vector;

	// 7.实现类的构造函数，初始化它的属性值。
	public Decrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}

	// 8.实现 run() 方法。使用 getAndDecrement() 方法操作array里的所有元素。
	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndDecrement(i);
		}
	}

}