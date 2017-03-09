package com.lyj.multithread.sync.rwlock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁所提供的最重要的改进之一就是ReadWriteLock接口
 * 和唯一一个实现它的ReentrantReadWriteLock类。
 * 
 * 这个类提供两把锁，一把用于读操作和一把用于写操作。
 * 
 * 同时可以有多个线程执行读操作，但只有一个线程可以执行写操作。
 * 当一个线程正在执行一个写操作，不可能有任何线程执行读操作。
 * 
 * @author Ja0ck5
 *
 */
public class PricesInfo {
	private double price1;
	private double price2;
	
	private ReadWriteLock lock;

	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		lock = new ReentrantReadWriteLock();
	}
	
	public double getPrice1(){
		lock.readLock().lock();
		double p = price1;
		lock.readLock().unlock();
		return p;
	}

	public double getPrice2(){
		lock.readLock().lock();
		double p = price2;
		lock.readLock().unlock();
		return p;
	}
	
	public void setPrices(double price1,double price2){
		lock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		lock.writeLock().unlock();
	}
	
	
}
