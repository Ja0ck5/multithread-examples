package com.lyj.multithread;

public class MyThreadGroup extends ThreadGroup {
	// 必须声明一个拥有一个参数的构造方法，因为ThreadGroup类有一个没有参数的构造方法
	public MyThreadGroup(String name) {
		super(name);
	}

	// 覆盖 uncaughtException() 方法。ThreadGroup 类的其中一个线程抛出异常时，就会调用此方法
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}

	
}
