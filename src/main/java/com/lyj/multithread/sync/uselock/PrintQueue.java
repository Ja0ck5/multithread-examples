package com.lyj.multithread.sync.uselock;

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
 * @author Ja0ck5
 *
 */
public class PrintQueue {
	private final Lock queueLock = new ReentrantLock();

	public void printJob(Object document) {
		// 调用lock()方法来获取Lock对象的控制权
		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during "
					+ (duration / 1000) + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放Lock对象的控制
			queueLock.unlock();
		}
		/**
		 * Lock
		 * 接口（和ReentrantLock类）包含其他方法来获取锁的控制权，那就是tryLock()方法。这个方法与lock()方法的最大区别是，
		 * 如果一 个线程调用这个方法不能获取Lock接口的控制权时，将会立即返回并且不会使这个线程进入睡眠。这个方法返回一个boolean值，
		 * true表示这个线程 获取了锁的控制权，false则表示没有。
		 * 
		 * 注释：考虑到这个方法的结果，并采取相应的措施，这是程序员的责任。如果这个方法返回false值，预计你的程序不会执行这个临界区。如果是这样，
		 * 你可能会在你的应用程序中得到错误的结果。
		 * 
		 * ReentrantLock类也允许递归调用（锁的可重入性，译者注），当一个线程有锁的控制权并且使用递归调用，它延续了锁的控制权，
		 * 所以调用lock()方法将会立即返回并且继续递归调用的执行。此外，我们也可以调用其他方法。
		 */
	}

}
