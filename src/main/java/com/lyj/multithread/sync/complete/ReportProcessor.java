package com.lyj.multithread.sync.complete;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
	private CompletionService<String> service;
	private boolean end;

	public ReportProcessor(CompletionService<String> service) {
		this.service = service;
		this.end = false;
	}

	@Override
	public void run() {// 调用CompletionService接口的poll()方法，获取CompletionService执行的下个已完成任务的Future对象
		while (!end) {
			try {
				Future<String> result = service.poll(20, TimeUnit.SECONDS);
				if (result != null) {
					String report = result.get();
					System.out.printf("ReportReceiver: Report Received:%s\n", report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("ReportSender: End\n");
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

}
