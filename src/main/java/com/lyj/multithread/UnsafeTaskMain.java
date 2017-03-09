package com.lyj.multithread;

import java.util.concurrent.TimeUnit;

public class UnsafeTaskMain {

	public static void main(String[] args) {
	/*	UnsafeTask task = new UnsafeTask();
		for (int i = 0; i < 10; i++) {

			new Thread(task).start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/

		
		SafeTask safeTask = new SafeTask();
		for (int i = 0; i < 10; i++) {
			
			new Thread(safeTask).start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
