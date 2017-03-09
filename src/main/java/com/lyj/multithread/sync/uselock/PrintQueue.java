package com.lyj.multithread.sync.uselock;

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
 * @author Ja0ck5
 *
 */
public class PrintQueue {
	private final Lock queueLock = new ReentrantLock();

	public void printJob(Object document) {
		// ����lock()��������ȡLock����Ŀ���Ȩ
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during "
					+ (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// �ͷ�Lock����Ŀ���
			queueLock.unlock();
		}
		/**
		 * Lock
		 * �ӿڣ���ReentrantLock�ࣩ����������������ȡ���Ŀ���Ȩ���Ǿ���tryLock()���������������lock()��������������ǣ�
		 * ���һ ���̵߳�������������ܻ�ȡLock�ӿڵĿ���Ȩʱ�������������ز��Ҳ���ʹ����߳̽���˯�ߡ������������һ��booleanֵ��
		 * true��ʾ����߳� ��ȡ�����Ŀ���Ȩ��false���ʾû�С�
		 * 
		 * ע�ͣ����ǵ���������Ľ��������ȡ��Ӧ�Ĵ�ʩ�����ǳ���Ա�����Ρ���������������falseֵ��Ԥ����ĳ��򲻻�ִ������ٽ����������������
		 * ����ܻ������Ӧ�ó����еõ�����Ľ����
		 * 
		 * ReentrantLock��Ҳ����ݹ���ã����Ŀ������ԣ�����ע������һ���߳������Ŀ���Ȩ����ʹ�õݹ���ã������������Ŀ���Ȩ��
		 * ���Ե���lock()���������������ز��Ҽ����ݹ���õ�ִ�С����⣬����Ҳ���Ե�������������
		 */
	}

}
