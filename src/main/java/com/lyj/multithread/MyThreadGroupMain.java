package com.lyj.multithread;

public class MyThreadGroupMain {
	
	public static void main(String[] args) {
		MyThreadGroup threadGroup = new MyThreadGroup("myThreadGroup");
		MyThreadGroupTask task = new MyThreadGroupTask();
		for (int i = 0; i < 2; i++) {
			new Thread(threadGroup, task).start();
		}
	}

}
