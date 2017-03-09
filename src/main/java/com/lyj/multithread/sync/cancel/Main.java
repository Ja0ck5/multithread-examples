package com.lyj.multithread.sync.cancel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ��ʾ��������֮������ȡ����ȷʵ��Thread.sleep(100)��أ���ΪFuture.cancel()��������ʵ�Ƿ���һ���ж�����
 * ��sleep�ܹ���Ӧ�жϣ�����ܴﵽȡ������ִ�������Ŀ�ġ�Ҳ����˵ֻҪִ���е������ܹ���Ӧ�жϣ�����ͨ��cancel()������ȡ�������ִ�У�
 * ���Բ�һ��Ҫ��Thread.sleep(100)�������Thread.interrupted()���ж�Ҳ�С�
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
