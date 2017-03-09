package com.lyj.multithread;

import java.util.concurrent.TimeUnit;

public class FileClockMain {
	public static void main(String[] args) {
		Thread thread = new Thread(new FileClock());
		thread.start();
		
		try {
			//调用sleep()方法， Thread 离开CPU并在一段时间内停止运行。在这段时间内，它是不消耗CPU时间的，使用可以执行其他任务。
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
		//yield() 方法, 它向JVM表示线程对象可以让CPU执行其他任务。JVM 不保证它会遵守请求。通常，它只是用来试调的。
	}
}
