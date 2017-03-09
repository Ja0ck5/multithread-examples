package com.lyj.multithread.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
	/**
	 * counter 整数，用来储存线程对象的数量
	 */
	private int counter;
	/**
	 * name 字符串，每个创建线程的名字；
	 */
	private String name;
	/**
	 * stats, 字符串list, 储存创建的线程对象的统计数据
	 */
	private List<String> stats;

	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	// 接收Runnable接口并返回一个 Thread 对象给这个 Runnable 接口。
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
