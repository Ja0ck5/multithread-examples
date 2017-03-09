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
			 * ʹ��schedule()��������ִ������һ��ʱ���ִ�����������������3�����������£�
			 * 
			 * 1. ��Ҫִ�е����� 2. ��Ҫ��������ִ��ǰ�ȴ��೤ʱ�� 3. ʱ�䵥λ��ָ��ΪTimeUnit��ĳ���
			 */
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}

		/*
		 * ��������ScheduledThreadPoolExecutor����Ϊ��������shutdown()����ʱ��
		 * �����д�������������ڵȴ������ӳٽ�����
		 * Ĭ�ϵ���Ϊ�ǣ�����ִ�����Ƿ������Щ���񶼽���ִ�С�
		 * 
		 * ����ʹ��ScheduledThreadPoolExecutor���
		 * setExecuteExistingDelayedTasksAfterShutdownPolicy()�������ı�������Ϊ��
		 * ʹ��false������ shutdown()ʱ������������񲻻ᱻִ�С�
		 */
		executor.shutdown();

		// awaitTermination()�������ȴ������������
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Main: Ends at: %s\n", new Date());

	}
}
