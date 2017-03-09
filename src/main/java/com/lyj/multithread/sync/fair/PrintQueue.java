package com.lyj.multithread.sync.fair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 它允许以一种更灵活的方式来构建synchronized块。使用synchronized关键字，
 * 你必须以结构化方式得到释放synchronized代码块的控制权。
 * 
 * 1. Lock接口允许你获得更复杂的结构来实现你的临界区。
 * 
 * 2. Lock接口比synchronized关键字提供更多额外的功能。 新功能之一是实现的tryLock()方法。
 * 这种方法试图获取锁的控制权并且如果它不能获取该锁，是因为其他线程在使用这个锁，它将返回这个锁。
 * 
 * 使用synchronized关键字，当线程A试图执行synchronized代码块，如果线程B正在执行它，
 * 那么线程A将阻塞直到线程B执行完synchronized代码块。 使用锁，你可以执行tryLock()方法，这个方法返回一个
 * Boolean值表示，是否有其他线程正在运行这个锁所保护的代码。
 * 
 * 3. 当有多个读者和一个写者时，Lock接口允许读写操作分离。
 * 
 * 4. Lock接口比synchronized关键字提供更好的性能。
 * 
 * 
 * 公平锁指的是哪个线程先运行，那就可以先得到锁。
 * 
 * 非公平锁是不管线程是否是先运行，都是随机获得锁的。
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
