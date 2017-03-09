package com.lyj.multithread.sync.rejected;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
	public static void main(String[] args) {
		RejectedTaskController controller = new RejectedTaskController();

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		// 指定自定义的 rejectedExcecutionHandler
		executor.setRejectedExecutionHandler(controller);
		System.out.printf("Main: Starting.\n");
		for (int i = 0; i < 3; i++) {
			MyTask task = new MyTask("Task" + i);
			executor.submit(task);
		}
		// 关闭
		System.out.printf("Main: Shutting down the Executor.\n");
		executor.shutdown();

		// 再提交
		System.out.printf("Main: Sending another Task.\n");
		MyTask task = new MyTask("RejectedTask");
		executor.submit(task);

		System.out.println("Main: End");
		System.out.printf("Main: End.\n");
	}
}
