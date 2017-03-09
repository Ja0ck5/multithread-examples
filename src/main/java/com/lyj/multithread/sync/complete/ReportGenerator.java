package com.lyj.multithread.sync.complete;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * CompletionService 类 有一个方法来提交任务给执行者 另一个方法来获取已完成执行的下个任务的Future对象。
 * 
 * 在内部实现中，它使用Executor对象执行任务。
 * 这种行为的优点是共享一个CompletionService对象，并提交任务给执行者，这样其他（对象）可以处理结果。
 * 
 * 其局限性是，第二个对象只能获取那些已经完成它们的执行的任务的Future对象，所以，这些Future对象只能获取任务的结果。
 * 
 * @author Ja0ck5
 *
 */
public class ReportGenerator implements Callable<String> {

	private String sender;
	private String title;

	public ReportGenerator(String sender, String title) {
		super();
		this.sender = sender;
		this.title = title;
	}

	@Override
	public String call() throws Exception {
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n", this.sender,
					this.title, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String ret = sender + ": " + title;
		return ret;
	}

}
