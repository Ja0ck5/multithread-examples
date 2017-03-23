package com.lyj.multithread.customexecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyExecutor extends ThreadPoolExecutor {

	private ConcurrentHashMap<String, Date> startTimes;

	public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		// 调用父类的构造器
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// 初始化startTime属性
		startTimes = new ConcurrentHashMap<>();
	}

	// 覆盖shutdown()方法。将关于已执行的任务，正在运行的任务和待处理的任务信息写入到控制台。然后，使用super关键字调用父类的shutdown()方法。
	@Override
	public void shutdown() {
		System.out.printf("MyExecutor: Going to shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks:%d\n", getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
		System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
		super.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		System.out.printf("MyExecutor: Going to immediately shutdown.\n");
		System.out.printf("MyExecutor: Executed tasks: %d\n", getCompletedTaskCount());
		System.out.printf("MyExecutor: Running tasks: %d\n", getActiveCount());
		System.out.printf("MyExecutor: Pending tasks: %d\n", getQueue().size());
		return super.shutdownNow();
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		System.out.printf("MyExecutor: A task is beginning: %s : %s\n", t.getName(), r.hashCode());
		startTimes.put(String.valueOf(r.hashCode()), new Date());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Future<?> result = (Future<?>) r;
		try {
			System.out.printf("*********************************\n");
			System.out.printf("MyExecutor: A task is finishing.\n");
			System.out.printf("MyExecutor: Result: %s\n", result.get());
			Date startDate = startTimes.remove(String.valueOf(r.hashCode()));
			Date finishDate = new Date();
			long diff = finishDate.getTime() - startDate.getTime();
			System.out.printf("MyExecutor: Duration: %d\n", diff);
			System.out.printf("*********************************\n");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
