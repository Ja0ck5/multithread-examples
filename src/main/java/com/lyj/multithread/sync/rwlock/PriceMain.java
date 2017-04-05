package com.lyj.multithread.sync.rwlock;

public class PriceMain {
	public static void main(String[] args) {
		PricesInfo pricesInfo = new PricesInfo();
		ReadPrices[] readers = new ReadPrices[5];
		Thread[] readerThreads = new Thread[5];
		
		for (int i = 0; i < readerThreads.length; i++) {
			readers[i] = new ReadPrices(pricesInfo);
			readerThreads[i] = new Thread(readers[i]);
		}
		
		WritePrices writePrices = new WritePrices(pricesInfo);
		Thread wt = new Thread(writePrices);
		
		for (int i = 0; i < readerThreads.length; i++) {
			readerThreads[i].start();
		}
		wt.start();
	}
}
