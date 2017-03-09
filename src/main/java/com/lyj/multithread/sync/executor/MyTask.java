package com.lyj.multithread.sync.executor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {

	private Date initDate;
	private String name;

	public MyTask(String name) {
		this.initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %dseconds\n", Thread.currentThread().getName(), name,
					duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
	}

}
