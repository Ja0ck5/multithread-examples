package com.lyj.multithread.sync.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者与消费者之间共享的缓冲区
 * 
 * 
 * condition的操作一定是在
 * 
 * 以调用Lock对象的lock()方法为开头
 * 
 * 以调用相同 Lock对象的unlock()方法为结尾
 * 
 * 的代码块中
 * 
 * 当一个线程在一个condition上调用await()方法时，它将自动释放锁的控制，所以其他线程可以获取这个锁的控制并开始执行相同操作，
 * 或者由同个锁保护的其他临界区。
 * 
 * 注释：当一个线程在一个condition上调用signal()或signallAll()方法，一个或者全部在这个condition上等待的线程将被唤醒。
 * 这并不能保证的使它们现在睡眠的条件现在是true，所以你必须在while循环内部调用await()方法。你不能离开这个循环，直到
 * condition为true。当condition为false，你必须再次调用 await()方法。
 * 
 * 你必须十分小心
 * ，在使用await()和signal()方法时。如果你在condition上调用await()方法而却没有在这个condition上调用signal()
 * 方法，这个线程将永远睡眠下去。
 * 
 * 在调用await()方法后，一个线程可以被中断的，所以当它正在睡眠时，你必须处理InterruptedException异常。
 * 
 * @author Ja0ck5
 *
 */
public class MyBuf {
	/**
	 * 存储共享数据
	 */
	private LinkedList<String> buffer;

	/**
	 * 存储缓冲区的大小
	 */
	private int maxSize;
	/**
	 * 用来控制修改缓冲区代码块的访问
	 */
	private ReentrantLock lock;

	private Condition lines;
	private Condition space;

	/**
	 * 表明如果缓冲区中有行
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
	 * 接收一个String类型参数并试图将它存储到缓冲区。
	 * 
	 * 1. 它获得锁的控制。当它有锁的控制，它将检查缓冲区是否有空闲空间。
	 * 
	 * 2. 如果缓冲区已满，它将调用await()方法在space条件上等待释放空间。
	 * 
	 * 3. 如果其他线程在space条件上调用signal()或signalAll()方法，这个线程将被唤醒。
	 * 
	 * 4. 当这种情况发生，这个线程在缓冲区上存储行并且在lines条件上调用signallAll()方法， 这个条件将会唤醒所有在缓冲行上等待的线程
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
	 * 它返回存储在缓冲区上的第一个字符串。
	 * 
	 * 1. 它获取锁的控制。当它拥有锁的控制，它检查缓冲区是否有行。
	 * 
	 * 2. 如果缓冲区是空的，它调用 await() 方法在 lines 条件上等待缓冲区中的行。
	 * 
	 * 3. 如果其他线程在lines条件上调用signal()或signalAll()方法，这个线程将被唤醒。
	 * 
	 * 4. 当它发生时，这个方法获取缓冲区的首行，并且在space条件上调用signalAll()方法，然后返回String。
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
	 * 用来设置pendingLines的值。当没有更多的行生产时，它将被生产者调用
	 * 
	 * @param pendingLines
	 */
	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}

	/**
	 * 如果有更多的行被处理时，返回true，否则返回false
	 * 
	 * @return
	 */
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}

}
