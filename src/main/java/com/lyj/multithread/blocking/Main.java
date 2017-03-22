package com.lyj.multithread.blocking;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception {
		LinkedBlockingDeque<String> list = new LinkedBlockingDeque<String>(3);

		Client client = new Client(list);
		Thread thread = new Thread(client);
		thread.start();

		// take()方法，每300毫秒获取列表的3个字符串(String)对象。重复这个循环5次。将字符串(String)写入到控制台
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				String request = list.take();
				System.out.printf("Main: Request: %s at %s. Size:%d\n", request, new Date(), list.size());
			}
			TimeUnit.MILLISECONDS.sleep(300);
		}
		System.out.printf("Main: End of the program.\n");
	}

}
