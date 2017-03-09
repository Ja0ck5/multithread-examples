package com.lyj.multithread.sync.cancel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 本示例的任务之所以能取消，确实与Thread.sleep(100)相关，因为Future.cancel()方法，其实是发送一个中断请求，
 * 而sleep能够响应中断，因此能达到取消正在执行任务的目的。也就是说只要执行中的任务能够响应中断，便能通过cancel()方法来取消任务的执行，
 * 所以不一定要用Thread.sleep(100)，诸如对Thread.interrupted()的判断也行。
 * 
 * @author Ja0ck5
 *
 */
public class Main {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		MyTask task = new MyTask();
		System.out.printf("Main: Executing the Task\n");
		Future<String> result = executor.submit(task);

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Canceling the Task\n");
		result.cancel(true);

		System.out.printf("Main: Canceled: %s\n", result.isCancelled());
		System.out.printf("Main: Done: %s\n", result.isDone());

		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");

	}
}
