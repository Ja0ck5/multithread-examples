package com.lyj.multithread.sync.complete;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * CompletionService �� ��һ���������ύ�����ִ���� ��һ����������ȡ�����ִ�е��¸������Future����
 * 
 * ���ڲ�ʵ���У���ʹ��Executor����ִ������
 * ������Ϊ���ŵ��ǹ���һ��CompletionService���󣬲��ύ�����ִ���ߣ��������������󣩿��Դ�������
 * 
 * ��������ǣ��ڶ�������ֻ�ܻ�ȡ��Щ�Ѿ�������ǵ�ִ�е������Future�������ԣ���ЩFuture����ֻ�ܻ�ȡ����Ľ����
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
