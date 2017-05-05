package com.lyj.test;

public class FullRunnable implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("========= full runnable ========");
	}

}
