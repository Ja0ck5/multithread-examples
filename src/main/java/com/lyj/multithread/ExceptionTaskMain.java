package com.lyj.multithread;

public class ExceptionTaskMain {

	/**
	 * The Thread ����������ط������Դ���δ������쳣����̬���� setDefaultUncaughtExceptionHandler()
	 * ΪӦ����������̶߳������쳣 handler ��
	 * 
	 * ��һ��δ��׽���쳣���߳��ﱻ�׳���JVM��Ѱ�Ҵ��쳣��3�ֿ���Ǳ�ڵĴ����ߣ�handler����
	 * 
	 * 1, ��Ѱ�����δ��׽���̶߳�����쳣handle��
	 * 
	 * ������handle�����ڣ�
	 * 
	 * 2, ��ôJVM�����̶߳����ThreadGroup��Ѱ�ҷǲ�׽�쳣��handler�����ڴ����߳����ڵĲ��ܿ����쳣����ܵ�������
	 * 
	 * ����˷��������ڣ�
	 * 
	 * 3, ��ô JVM ��Ѱ��Ĭ�Ϸǲ�׽�쳣handle��
	 * 
	 * ���û��һ��handler����, 
	 * 
	 * 4, ��ô JVM����쳣�� stack trace д��ٿ�̨����������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionTask et = new ExceptionTask();
		Thread thread = new Thread(et);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}

}
