package com.lyj.multithread.sync.procon;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EventStorage {
	private int maxSize;
	private List<Date> storage;

	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Adds the specified element as the tail (last element) of this list.
		((LinkedList<Date>) storage).offer(new Date());
		System.out.printf("Set: %d", storage.size());
		notifyAll();
	}

	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Retrieves and removes the head (first element) of this list
		System.out.printf("Get: %d: %s", storage.size(), ((LinkedList<?>) storage).poll());
		notifyAll();
	}
}
