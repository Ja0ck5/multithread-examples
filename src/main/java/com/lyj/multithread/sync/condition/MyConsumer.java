package com.lyj.multithread.sync.condition;

import java.util.Random;

public class MyConsumer implements Runnable {
	private MyBuf buffer;

	public MyConsumer(MyBuf buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (buffer.hasPendingLines()) {
			String line = buffer.get();
			processLine(line);
		}
	}

	/**
	 * 模拟某种行的处理
	 * 
	 * @param line
	 */
	private void processLine(String line) {
		try {
			Thread.sleep(new Random().nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
