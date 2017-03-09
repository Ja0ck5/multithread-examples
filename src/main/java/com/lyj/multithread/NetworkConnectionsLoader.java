package com.lyj.multithread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable{

	@Override
	public void run() {
		System.out.printf("Begining data sources loading : %s\n", new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Data sources has been finished : %s\n", new Date());
	}
}
