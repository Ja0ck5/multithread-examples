package com.lyj.multithread.sync.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyServer {

	private ThreadPoolExecutor executor;

	public MyServer() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	// ʵ��executeTask()����������Task������Ϊ�����������ύ��ִ���ߡ����ȣ�д��һ����Ϣ������̨��������һ���µ����񵽴�
	public void executeTask(MyTask task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	//����ִ���ߵ�shutdown()��������������ִ��
	public void endServer() {
		executor.shutdown();
	}

}
