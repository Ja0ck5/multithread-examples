package com.lyj.multithread;

import java.util.concurrent.TimeUnit;

public class SearchTaskMain {
	// ThreadGroup �ഢ���̶߳���������й����� ThreadGroup �������������Է������ǵ�������Ϣ (���磬״̬)
	// ��ȫ����Ա�Ĳ������� (���磬�ж�)��
	public static void main(String[] args) {
		// ����һ�� ThreadGroup
		ThreadGroup threadGroup = new ThreadGroup("search");
		ResultName result = new ResultName();
		SearchTask searchTask = new SearchTask(result);
		// ʹ��SearchTask���󴴽� 10�� Thread ���󡣵������Thread
		// ��Ĺ��캯��ʱ����������ΪThreadGroup����ĵ�һ������
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// ʹ��list() ����д���� ThreadGroup ob������Ϣ
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
