package com.lyj.multithread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Java有一种特别的线程叫做守护线程。 这种线程的优先级非常低，通常在程序里没有其他线程运行时才会执行它。
 * 当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序。
 * 
 * @author Ja0ck5
 *
 */
public class WriterTask implements Runnable {

	private Deque<Event> deque;

	public WriterTask(Deque<Event> deque) {
		this.deque = deque;
	}

	// 在一个线程 对象的 run() 方法里抛出一个检查异常，我们必须捕获并处理他们。因为 run() 方法不接受 throws 子句。
	// 是因为线程的run方法是从父类Thread 继承 或者是 实现 Runnable 过来的
	// 覆盖其父类方法时不能抛出父类没有声明的可捕获异常。
	// 所以run方法中不能抛出任何的exception
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
