package com.lyj.multithread;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {

	private Deque<Event> deque;

	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			clean(new Date());
		}

	}

	/**
	 * ����ȡ�����¼����������10��ǰ����������ɾ�������鿴��һ���¼���
	 * ���һ���¼���ɾ��������дһ���¼���Ϣ��queue���µĴ�С��
	 * Ϊ�����㿴���仯����
	 * 
	 * @param date
	 */
	private void clean(Date date) {
		long diff;
		boolean isDeleted;
		
		if(deque.size() == 0) return;
		isDeleted = false;
		do{
			Event last = deque.getLast();
			diff = date.getTime() - last.getDate().getTime();
			if(diff > 10000){
				System.out.printf("Cleaner : %s\n",last.getEvent());
				deque.removeLast();
				isDeleted = true;
			}
		}while(diff > 10000);
		if(isDeleted)
			System.out.printf("Cleaner : Size of deque : %d\n",deque.size());
	}

}
