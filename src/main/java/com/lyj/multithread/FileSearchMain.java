package com.lyj.multithread;

import java.util.concurrent.TimeUnit;

public class FileSearchMain {

	public static void main(String[] args) {
		FileSearch fileSearch = new FileSearch("C:\\Users\\Ja0ck5\\Desktop","log.txt");
		Thread thread = new Thread(fileSearch);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
