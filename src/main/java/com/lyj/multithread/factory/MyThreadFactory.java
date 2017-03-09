package com.lyj.multithread.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
	/**
	 * counter ���������������̶߳��������
	 */
	private int counter;
	/**
	 * name �ַ�����ÿ�������̵߳����֣�
	 */
	private String name;
	/**
	 * stats, �ַ���list, ���洴�����̶߳����ͳ������
	 */
	private List<String> stats;

	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	// ����Runnable�ӿڲ�����һ�� Thread �������� Runnable �ӿڡ�
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;
		stats.add(String.format("created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));

		return t;
	}

	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();
		while (it.hasNext()) {
			buffer.append(it.next());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
