package com.lyj.multithread.sync.procon;

public class EventStorageMain {
	
	public static void main(String[] args) {
		EventStorage storage = new EventStorage();
		MyProducer myProducer = new MyProducer(storage);
		Thread pt = new Thread(myProducer);
	
		MyConsumer myConsumer = new MyConsumer(storage);
		Thread ct = new Thread(myConsumer);
	
		pt.start();
		ct.start();
	}

}
