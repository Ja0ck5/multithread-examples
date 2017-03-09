package com.lyj.multithread.sync.procon;

public class MyConsumer implements Runnable {
	
	private EventStorage storage;
	

	public MyConsumer(EventStorage storage) {
		this.storage = storage;
	}


	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

}
