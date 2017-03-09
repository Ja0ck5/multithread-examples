package com.lyj.multithread.sync.procon;

public class MyProducer implements Runnable {

	private EventStorage storage;

	public MyProducer(EventStorage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}

}
