package com.lyj.multithread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Java��һ���ر���߳̽����ػ��̡߳� �����̵߳����ȼ��ǳ��ͣ�ͨ���ڳ�����û�������߳�����ʱ�Ż�ִ������
 * ���ػ��߳��ǳ�����Ψһ�����е��߳�ʱ��JVM������ػ��̲߳���ֹ����
 * 
 * @author Ja0ck5
 *
 */
public class WriterTask implements Runnable {

	private Deque<Event> deque;

	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	// ��һ���߳� ����� run() �������׳�һ������쳣�����Ǳ��벶�񲢴������ǡ���Ϊ run() ���������� throws �Ӿ䡣
	// ����Ϊ�̵߳�run�����ǴӸ���Thread �̳� ������ ʵ�� Runnable ������
	// �����丸�෽��ʱ�����׳�����û�������Ŀɲ����쳣��
	// ����run�����в����׳��κε�exception
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			Event event = new Event();
			event.setDate(new Date());
			event.setEvent(String.format("The thread : %s has generated an event", Thread.currentThread().getId()));
			deque.addFirst(event);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		throw new RuntimeException();
	}

}
