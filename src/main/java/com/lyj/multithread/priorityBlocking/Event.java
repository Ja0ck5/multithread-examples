package com.lyj.multithread.priorityBlocking;

public class Event implements Comparable<Event> {
	/**
	 * 存储已创建事件的线程数
	 */
	private int thread;
	/**
	 * 用来存储事件的优先级
	 */
	private int priority;

	public Event(int thread, int priority) {
		super();
		this.thread = thread;
		this.priority = priority;
	}

	public int getThread() {
		return thread;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(Event e) {
		if (this.priority > e.getPriority()) {
			return -1;
		} else if (this.priority < e.getPriority()) {
			return 1;
		} else {
			return 0;
		}
	}

}
