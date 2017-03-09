package com.lyj.multithread.sync.scheduled.period;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 当你想要使用执行者框架执行一个周期性任务，你需要ScheduledExecutorService对象。Java建议使用
 * Executors类创建执行者，Executors类是一个执行者对象工厂。在本例中，你应该使用newScheduledThreadPool()方法，
 * 创建一个 ScheduledExecutorService对象。
 * 
 * @author Ja0ck5
 *
 */
public class Main {
	public static void main(String[] args) {

		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n", new Date());

		MyTask task = new MyTask("task");
		// 使用scheduledAtFixRate()方法把它提交给执行者。使用前面创建的任务，数字1，数字2和常量TimeUnit.SECONDS作为参数。这个方法返回ScheduledFuture对象，它可以用来控制任务的状态。
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

		// 在循环中，使用ScheduledFuture对象的getDelay()方法，获取任务下次执行的毫秒数
		for (int i = 0; i < 10; i++) {
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			// 线程睡眠500毫秒
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/*
		 * 可以配置ScheduledThreadPoolExecutor的行为，当调用shutdown()方法时，
		 * 并且有待处理的任务正在等待它们延迟结束。 默认的行为是，不管执行者是否结束这些任务都将被执行。
		 * 
		 * 可以使用ScheduledThreadPoolExecutor类的
		 * setExecuteExistingDelayedTasksAfterShutdownPolicy()方法来改变这种行为。
		 * 使用false，调用 shutdown()时，待处理的任务不会被执行。
		 */
		executor.shutdown();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Finished at: %s\n", new Date());

	}
}
