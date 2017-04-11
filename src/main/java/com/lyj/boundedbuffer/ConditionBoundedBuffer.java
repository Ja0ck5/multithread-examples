package com.lyj.boundedbuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 每个内置锁都只能有一个相关联的条件队列。 多个线程可能在用一个条件队列上等待不同的条件谓词，并且子最常见的加锁模式下
 * 公开条件对垒对象。这些因素使得无法满足在使用 notifyAll 时所有的等待线程为同一类型的需求
 * 如果想编写一个带有多个条件谓词的并发对象，或者想获得除了条件队列可见性之外的更多控制权
 * 
 * 
 * 可以使用 显示 的Lock 和 Condition 一个 Condition 和 一个 Lock 关联在一起。Condition 比内置条件队列
 * 提供了更丰富的功能。 a. 在每个锁上可存在多个等待 b. 条件等待可以是可中断或不可中断的 c. 基于时限的等待 d. 公平或非公平的队列操作
 * 
 * Condition 对象继承了相关的 Lock 对象的公平性，对于公平的锁，线程依然会依照 FIFO 顺序从 Condition.await 中释放
 * 
 * @author Ja0ck5
 *
 * @param <T>
 */
public class ConditionBoundedBuffer<T> {
	private static final int BUFFER_SIZE = 1024;
	protected final Lock lock = new ReentrantLock();
	// 条件谓词
	private final Condition notFull = lock.newCondition();
	// 条件谓词
	private final Condition notEmpty = lock.newCondition();

	private final T[] items = (T[]) new Object[BUFFER_SIZE];
	private int tail, head, count;

	/**
	 * 阻塞直到 notFull ({@linkplain #count} count < {@linkplain #items}
	 * items.length)
	 * 
	 * @param t
	 * @throws InterruptedException
	 */
	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				// 满
				notFull.await();
			}
			items[tail] = t;
			if (++tail == items.length) {
				tail = 0;
			}
			++count;
			// 没空 的条件谓词发出通知
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 阻塞直到 notEmpty ---> ({@linkplain #count}> 0)
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public T take() throws InterruptedException {
		lock.lock();
		try {
			while (0 == count) {
				// 空
				notEmpty.await();
			}
			T t = items[head];
			items[head] = null;
			if (++head == items.length) {
				head = 0;
			}
			--count;
			// 没满 条件谓词发出通知
			notFull.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}
}
