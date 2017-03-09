package com.lyj.multithread;

import java.util.ArrayDeque;
import java.util.Deque;

public class CleanerTaskMain {
	
	public static void main(String[] args) {
		Deque<Event> deque = new ArrayDeque<Event>();
		WriterTask writer = new WriterTask(deque);
		
		for (int i = 0; i < 3; i++)
			new Thread(writer).start();
		
		new CleanerTask(deque).start();
	}
}
