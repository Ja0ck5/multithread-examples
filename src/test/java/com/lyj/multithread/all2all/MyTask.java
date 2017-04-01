package com.lyj.multithread.all2all;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyTask implements Callable<Result> {
	
	private String name;
	
	public MyTask(String name) {
		this.name = name;
	}

	@Override
	public  Result call() {
		
		System.out.printf("%s : Starting...  ... " ,this.name);
		
		long duration = (long) (Math.random() * 10);
		System.out.printf(" %s : waiting  %d seconds for results\n",this.name,duration);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int val = 0;
		for (int i = 0; i < 5; i++) {
			val += Math.random() * 100;
		}
		
		Result result = new Result();
		result.setName(name);
		result.setVal(val);
		
		System.out.printf("%s : Ends", this.name);
		return result;
	}

}
