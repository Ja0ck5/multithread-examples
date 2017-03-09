package com.lyj.multithread.sync.scheduled.period;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ������Ҫʹ��ִ���߿��ִ��һ����������������ҪScheduledExecutorService����Java����ʹ��
 * Executors�ഴ��ִ���ߣ�Executors����һ��ִ���߶��󹤳����ڱ����У���Ӧ��ʹ��newScheduledThreadPool()������
 * ����һ�� ScheduledExecutorService����
 * 
 * @author Ja0ck5
 *
 */
public class Main {
	public static void main(String[] args) {

		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n", new Date());

		MyTask task = new MyTask("task");
		// ʹ��scheduledAtFixRate()���������ύ��ִ���ߡ�ʹ��ǰ�洴������������1������2�ͳ���TimeUnit.SECONDS��Ϊ�����������������ScheduledFuture�����������������������״̬��
		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

		// ��ѭ���У�ʹ��ScheduledFuture�����getDelay()��������ȡ�����´�ִ�еĺ�����
		for (int i = 0; i < 10; i++) {
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			// �߳�˯��500����
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/*
		 * ��������ScheduledThreadPoolExecutor����Ϊ��������shutdown()����ʱ��
		 * �����д�������������ڵȴ������ӳٽ����� Ĭ�ϵ���Ϊ�ǣ�����ִ�����Ƿ������Щ���񶼽���ִ�С�
		 * 
		 * ����ʹ��ScheduledThreadPoolExecutor���
		 * setExecuteExistingDelayedTasksAfterShutdownPolicy()�������ı�������Ϊ��
		 * ʹ��false������ shutdown()ʱ������������񲻻ᱻִ�С�
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
