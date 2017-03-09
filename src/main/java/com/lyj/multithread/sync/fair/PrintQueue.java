package com.lyj.multithread.sync.fair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��������һ�ָ����ķ�ʽ������synchronized�顣ʹ��synchronized�ؼ��֣�
 * ������Խṹ����ʽ�õ��ͷ�synchronized�����Ŀ���Ȩ��
 * 
 * 1. Lock�ӿ��������ø����ӵĽṹ��ʵ������ٽ�����
 * 
 * 2. Lock�ӿڱ�synchronized�ؼ����ṩ�������Ĺ��ܡ� �¹���֮һ��ʵ�ֵ�tryLock()������
 * ���ַ�����ͼ��ȡ���Ŀ���Ȩ������������ܻ�ȡ����������Ϊ�����߳���ʹ������������������������
 * 
 * ʹ��synchronized�ؼ��֣����߳�A��ͼִ��synchronized����飬����߳�B����ִ������
 * ��ô�߳�A������ֱ���߳�Bִ����synchronized����顣 ʹ�����������ִ��tryLock()�����������������һ��
 * Booleanֵ��ʾ���Ƿ��������߳���������������������Ĵ��롣
 * 
 * 3. ���ж�����ߺ�һ��д��ʱ��Lock�ӿ������д�������롣
 * 
 * 4. Lock�ӿڱ�synchronized�ؼ����ṩ���õ����ܡ�
 * 
 * 
 * ��ƽ��ָ�����ĸ��߳������У��ǾͿ����ȵõ�����
 * 
 * �ǹ�ƽ���ǲ����߳��Ƿ��������У��������������ġ�
 * @author Ja0ck5
 *
 */
public class PrintQueue {
	/**
	 * fair lock
	 */
	private final Lock queueLock = new ReentrantLock(true);

	public void printJob(Object document) {
		
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during "
					+ (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
		
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during "
					+ (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}

}
