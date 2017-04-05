package com.lyj.multithread.sync.scheduled.period;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyTask implements Runnable{

	private String name;
	
	public MyTask(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
	}

}
