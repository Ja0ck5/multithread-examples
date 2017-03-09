package com.lyj.multithread.sync.all2all;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyTask implements Callable<Result> {

	
	private String name;
	
	public MyTask(String name) {
		this.name = name;
	}

	@Override
	public Result call() throws Exception {
		//Starting
		System.out.printf("%s: Starting\n",this.name);

		try {
			long duration = (long) (Math.random() *10);
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int val = 0;
		for (int i = 0; i < 5; i++) {
			val += Math.random()*100;
		}
		
		Result result = new Result();
		result.setName(name);
		result.setVal(val);
		System.out.println(this.name+": Ends");

		return result;
	}

	
}
