package com.lyj.multithread.sync.executable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask���ṩһ��done()��������������ִ����ִ��������ɺ�ִ��һЩ���롣�����������һЩ�������������һ�����棬ͨ��e-
 * mail���ͽ�������ͷ�һЩ��Դ��
 * 
 * ��ִ�е�������FutureTask��������ɣ�FutureTask���ڲ�������������� �������������Ľ�����ú�����״̬���isDone״̬֮�󱻵��ã�
 * ���������Ƿ��Ѿ���ȡ����������ɡ�
 * 
 * @author Ja0ck5
 *
 */
public class ExecutableTask implements Callable<String> {

	private String name;

	public ExecutableTask(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		try {
			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
		}
		return "Hello, fautureTask. I'm " + name;
	}

	public String getName() {
		return this.name;
	}

}
