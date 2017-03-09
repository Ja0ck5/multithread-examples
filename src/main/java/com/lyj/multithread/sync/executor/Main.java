package com.lyj.multithread.sync.executor;

public class Main {
	public static void main(String[] args) {
		MyServer server = new MyServer();
		for (int i = 0; i < 100; i++) {
			MyTask task = new MyTask("Task " + i);
			server.executeTask(task);
		}
		server.endServer();
	}
}
