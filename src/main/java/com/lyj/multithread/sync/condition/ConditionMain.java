package com.lyj.multithread.sync.condition;

public class ConditionMain {

	public static void main(String[] args) {
		FileMock fileMock = new FileMock(100, 10);
		MyBuf buffer = new MyBuf(20);
		
		//1 producer
		MyProducer producer = new MyProducer(fileMock, buffer);
		Thread threadProducer = new Thread(producer, "Producer");
		
		//2 consumer
		MyConsumer consumers[] = new MyConsumer[3];
		Thread threadConsumers[] = new Thread[3];
		for (int i = 0; i < 3; i++) {
			consumers[i] = new MyConsumer(buffer);
			threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
		}
		
		//3 start
		threadProducer.start();
		for (int i = 0; i < 3; i++) {
			threadConsumers[i].start();
		}

	}

}
