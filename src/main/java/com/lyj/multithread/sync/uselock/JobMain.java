package com.lyj.multithread.sync.uselock;

public class JobMain {

	public static void main(String[] args) {
		PrintQueue printQueue = new PrintQueue();

		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new MyJob(printQueue), "Thread " + i);
		}
		for (int i = 0; i < 10; i++) 
			thread[i].start();
	}

}
