package com.lyj.multithread.sync.rwlock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �����ṩ������Ҫ�ĸĽ�֮һ����ReadWriteLock�ӿ�
 * ��Ψһһ��ʵ������ReentrantReadWriteLock�ࡣ
 * 
 * ������ṩ��������һ�����ڶ�������һ������д������
 * 
 * ͬʱ�����ж���߳�ִ�ж���������ֻ��һ���߳̿���ִ��д������
 * ��һ���߳�����ִ��һ��д���������������κ��߳�ִ�ж�������
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
