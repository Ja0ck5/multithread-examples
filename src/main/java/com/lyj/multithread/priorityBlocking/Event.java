package com.lyj.multithread.priorityBlocking;

public class Event implements Comparable<Event> {
	/**
	 * �洢�Ѵ����¼����߳���
	 */
	private int thread;
	/**
	 * �����洢�¼������ȼ�
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
