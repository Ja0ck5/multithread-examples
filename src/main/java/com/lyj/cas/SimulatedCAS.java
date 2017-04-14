package com.lyj.cas;

/**
 * 1. CAS 包含了三个操作数： a. 需要读写的内存位置 V b. 进行比较的值 A c. 拟写入的新值 B
 * 
 * 2. 当且仅当 V 的值等于 A CAS 才会通过原子的方式用新值 B 来更新 V 的值 否则不会进行任何操作 无论操作是否成功都会返回 3. CAS
 * 使用一项乐观的技术，它希望能成功的执行更新操作 并且如果有另一个线程在最近一次检查后更新了该变量 那么 CAS 能检测到这个错误
 * 
 * @author Ja0ck5
 *
 *         SimulatedCAS 只说明语义，不是实现
 */
public class SimulatedCAS {

	private int value;

	public synchronized int get(){return value;};

	/**
	 * 当多个线程尝试使用 CAS 同时更新同一个变量时，只有其中一个线程能够更新变量的值
	 * 而其他的线程都将失败
	 * 然而，失败的线程不会被挂起，而是被告知在这次竞争中失败，并可以在此尝试
	 * 这与获取锁的情况是不同的，获取锁失败了，线程会被挂起
	 * @param expectedValue
	 * @param newValue
	 * @return
	 */
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if (oldValue == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}
	
	public synchronized boolean compareAndSet(int expectedValue,int newValue){
		return (expectedValue == compareAndSwap(expectedValue, newValue));
	}
}
