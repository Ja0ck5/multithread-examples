package com.lyj.multithread.sync.scheduled;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyTask implements Callable<String>{

	private String name;
	
	public MyTask(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
		return "Hello Schedule";
	}

}
