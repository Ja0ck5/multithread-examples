package com.lyj.multithread;

public class PrimeMain {
	
	public static void main(String[] args) {
		Thread task = new PrimeGenerator();
		task.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//ÖÐ¶Ï
		task.interrupt();
	}

}
