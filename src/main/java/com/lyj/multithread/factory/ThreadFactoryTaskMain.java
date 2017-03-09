package com.lyj.multithread.factory;

public class ThreadFactoryTaskMain {

	public static void main(String[] args) {
		MyThreadFactory myThreadFactory = new MyThreadFactory("myThreadFactory");
		ThreadFactoryTask task = new ThreadFactoryTask();
		System.out.printf("Starting the thread\n");
		for (int i = 0; i < 10; i++) {
			myThreadFactory.newThread(task).start();
		}
		System.out.printf("Factory stats:\n"); System.out.printf("%s\n",myThreadFactory.getStats());
	}
}
