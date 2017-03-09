package com.lyj.multithread.sync.cancel;

import java.util.concurrent.Callable;

public class MyTask implements Callable<String>{

	@Override
	public String call() throws Exception {
		while(true){
			System.out.println("test\n");
			Thread.sleep(100);
		}
	}

}
