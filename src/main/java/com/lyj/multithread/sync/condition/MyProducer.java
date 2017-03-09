package com.lyj.multithread.sync.condition;

public class MyProducer implements Runnable {

	private FileMock mock;
	private MyBuf buffer;

	public MyProducer(FileMock mock, MyBuf buffer) {
		super();
		this.mock = mock;
		this.buffer = buffer;
	}

	// 读取在FileMock对象中创建的所有行，并使用insert()方法将它们存储到缓冲区。
	// 一旦这个过程结束，使用setPendingLines()方法警告缓冲区，不会再产生更多的行
	@Override
	public void run() {
		buffer.setPendingLines(true);
		while (mock.hasMoreLines()) {
			String line = mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}

}
