package com.lyj.multithread.sync.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyServer {

	private ThreadPoolExecutor executor;

	public MyServer() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	// 实现executeTask()方法，接收Task对象作为参数并将其提交到执行者。首先，写入一条信息到控制台，表明有一个新的任务到达
	public void executeTask(MyTask task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	//调用执行者的shutdown()方法来结束任务执行
	public void endServer() {
		executor.shutdown();
	}

}
