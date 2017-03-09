package com.lyj.multithread;

import java.util.concurrent.TimeUnit;

public class FileClockMain {
	public static void main(String[] args) {
		Thread thread = new Thread(new FileClock());
		thread.start();
		
		try {
			//����sleep()������ Thread �뿪CPU����һ��ʱ����ֹͣ���С������ʱ���ڣ����ǲ�����CPUʱ��ģ�ʹ�ÿ���ִ����������
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread.interrupt();
		//yield() ����, ����JVM��ʾ�̶߳��������CPUִ����������JVM ����֤������������ͨ������ֻ�������Ե��ġ�
	}
}
