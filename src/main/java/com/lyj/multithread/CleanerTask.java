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
	 * 它获取最后的事件，如果它在10秒前被创建，就删除它并查看下一个事件。
	 * 如果一个事件被删除，它会写一个事件信息和queue的新的大小，
	 * 为了让你看到变化过程
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
