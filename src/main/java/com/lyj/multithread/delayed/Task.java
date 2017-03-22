package com.lyj.multithread.delayed;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class Task implements Runnable {

	private int id;

	private DelayQueue<Event> queue;

	public Task(int id, DelayQueue<Event> queue) {
		super();
		this.id = id;
		this.queue = queue;
	}

	@Override
	public void run() {
		// 计算任务将要创建的事件的激活日期。添加等于对象ID的实际日期秒数
		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + (id * 1000));
		System.out.printf("Thread %s: %s\n", id, delay);
		// 队列中存储100个事件
		for (int i = 0; i < 100; i++) {
			Event event = new Event(delay);
			queue.add(event);
		}
	}

}
