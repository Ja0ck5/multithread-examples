package com.lyj.multithread.sync.scheduled;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

		System.out.printf("Main: Starting at: %s\n", new Date());
		for (int i = 0; i < 5; i++) {
			MyTask task = new MyTask("Task " + i);
			// Creates and executes a ScheduledFuture that becomes enabled after
			// the given delay.
			/*
			 * 使用schedule()方法，让执行者在一段时间后执行任务。这个方法接收3个参数，如下：
			 * 
			 * 1. 想要执行的任务 2. 想要让任务在执行前等待多长时间 3. 时间单位，指定为TimeUnit类的常数
			 */
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}

		/*
		 * 可以配置ScheduledThreadPoolExecutor的行为，当调用shutdown()方法时，
		 * 并且有待处理的任务正在等待它们延迟结束。
		 * 默认的行为是，不管执行者是否结束这些任务都将被执行。
		 * 
		 * 可以使用ScheduledThreadPoolExecutor类的
		 * setExecuteExistingDelayedTasksAfterShutdownPolicy()方法来改变这种行为。
		 * 使用false，调用 shutdown()时，待处理的任务不会被执行。
		 */
		executor.shutdown();

		// awaitTermination()方法，等待所有任务完成
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Ends at: %s\n", new Date());

	}
}
