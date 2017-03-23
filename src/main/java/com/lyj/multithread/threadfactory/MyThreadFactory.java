package com.lyj.multithread.threadfactory;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
	
	private int counter;

	private String prefix;

	
	public MyThreadFactory(String prefix) {
		super();
		this.counter = 1;
		this.prefix = prefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		MyThread myThread=new MyThread(r,prefix+"-"+counter);
		counter++;
		return myThread;
	}
	
	

}
