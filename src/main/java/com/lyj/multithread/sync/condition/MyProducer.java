package com.lyj.multithread.sync.condition;

public class MyProducer implements Runnable {

	private FileMock mock;
	private MyBuf buffer;

	public MyProducer(FileMock mock, MyBuf buffer) {
		super();
		this.mock = mock;
		this.buffer = buffer;
	}

	// ��ȡ��FileMock�����д����������У���ʹ��insert()���������Ǵ洢����������
	// һ��������̽�����ʹ��setPendingLines()�������滺�����������ٲ����������
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
