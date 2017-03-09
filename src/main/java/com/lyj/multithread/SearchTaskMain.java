package com.lyj.multithread;

import java.util.concurrent.TimeUnit;

public class SearchTaskMain {
	// ThreadGroup 类储存线程对象和其他有关联的 ThreadGroup 对象，所以它可以访问他们的所有信息 (例如，状态)
	// 和全部成员的操作表现 (例如，中断)。
	public static void main(String[] args) {
		// 创建一个 ThreadGroup
		ThreadGroup threadGroup = new ThreadGroup("search");
		ResultName result = new ResultName();
		SearchTask searchTask = new SearchTask(result);
		// 使用SearchTask对象创建 10个 Thread 对象。当你调用Thread
		// 类的构造函数时，传递它作为ThreadGroup对象的第一个参数
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 使用list() 方法写关于 ThreadGroup ob对象信息
		System.out.printf("Number of threads : %d\n", threadGroup.activeCount());
		System.out.printf("Information about the thread group\n");
		/*
		 * Prints information about this thread group to the standard output.
		 * This method is useful only for debugging.
		 */
		threadGroup.list();

		waitFinish(threadGroup);
		// Interrupts all threads in this thread group
		threadGroup.interrupt();
	}

	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
