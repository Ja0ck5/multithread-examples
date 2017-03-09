package com.lyj.multithread.sync.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��������������֮�乲��Ļ�����
 * 
 * 
 * condition�Ĳ���һ������
 * 
 * �Ե���Lock�����lock()����Ϊ��ͷ
 * 
 * �Ե�����ͬ Lock�����unlock()����Ϊ��β
 * 
 * �Ĵ������
 * 
 * ��һ���߳���һ��condition�ϵ���await()����ʱ�������Զ��ͷ����Ŀ��ƣ����������߳̿��Ի�ȡ������Ŀ��Ʋ���ʼִ����ͬ������
 * ������ͬ���������������ٽ�����
 * 
 * ע�ͣ���һ���߳���һ��condition�ϵ���signal()��signallAll()������һ������ȫ�������condition�ϵȴ����߳̽������ѡ�
 * �Ⲣ���ܱ�֤��ʹ��������˯�ߵ�����������true�������������whileѭ���ڲ�����await()�������㲻���뿪���ѭ����ֱ��
 * conditionΪtrue����conditionΪfalse��������ٴε��� await()������
 * 
 * �����ʮ��С��
 * ����ʹ��await()��signal()����ʱ���������condition�ϵ���await()������ȴû�������condition�ϵ���signal()
 * ����������߳̽���Զ˯����ȥ��
 * 
 * �ڵ���await()������һ���߳̿��Ա��жϵģ����Ե�������˯��ʱ������봦��InterruptedException�쳣��
 * 
 * @author Ja0ck5
 *
 */
public class MyBuf {
	/**
	 * �洢��������
	 */
	private LinkedList<String> buffer;

	/**
	 * �洢�������Ĵ�С
	 */
	private int maxSize;
	/**
	 * ���������޸Ļ����������ķ���
	 */
	private ReentrantLock lock;

	private Condition lines;
	private Condition space;

	/**
	 * �������������������
	 */
	private boolean pendingLines;

	public MyBuf(int maxSize) {
		this.maxSize = maxSize;
		buffer = new LinkedList<>();
		lock = new ReentrantLock();
		lines = lock.newCondition();
		space = lock.newCondition();
		pendingLines = true;
	}

	/**
	 * ����һ��String���Ͳ�������ͼ�����洢����������
	 * 
	 * 1. ��������Ŀ��ơ����������Ŀ��ƣ�������黺�����Ƿ��п��пռ䡣
	 * 
	 * 2. �����������������������await()������space�����ϵȴ��ͷſռ䡣
	 * 
	 * 3. ��������߳���space�����ϵ���signal()��signalAll()����������߳̽������ѡ�
	 * 
	 * 4. �������������������߳��ڻ������ϴ洢�в�����lines�����ϵ���signallAll()������ ����������ỽ�������ڻ������ϵȴ����߳�
	 * 
	 * @param line
	 */
	public void insert(String line) {
		lock.lock();
		try {
			while (buffer.size() == maxSize) {
				space.await();
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
			lines.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * �����ش洢�ڻ������ϵĵ�һ���ַ�����
	 * 
	 * 1. ����ȡ���Ŀ��ơ�����ӵ�����Ŀ��ƣ�����黺�����Ƿ����С�
	 * 
	 * 2. ����������ǿյģ������� await() ������ lines �����ϵȴ��������е��С�
	 * 
	 * 3. ��������߳���lines�����ϵ���signal()��signalAll()����������߳̽������ѡ�
	 * 
	 * 4. ��������ʱ�����������ȡ�����������У�������space�����ϵ���signalAll()������Ȼ�󷵻�String��
	 * 
	 * @return
	 */
	public String get() {
		String line = null;
		lock.lock();
		try {
			while ((buffer.size() == 0) && (hasPendingLines())) {
				lines.await();
			}
			if (hasPendingLines()) {
				line = buffer.poll();
				System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());

				space.signalAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return line;
	}

	/**
	 * ��������pendingLines��ֵ����û�и����������ʱ�������������ߵ���
	 * 
	 * @param pendingLines
	 */
	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}

	/**
	 * ����и�����б�����ʱ������true�����򷵻�false
	 * 
	 * @return
	 */
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}

}
