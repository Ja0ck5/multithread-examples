package com.lyj.multithread.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Account {

	private AtomicLong balance;

	public Account(AtomicLong balance) {
		super();
		this.balance = balance;
	}

	public Account() {
	}

	public AtomicLong getBalance() {
		return balance;
	}

	public void setBalance(AtomicLong balance) {
		this.balance = balance;
	}

	// ʵ��һ����������Ϊ addAmount()���������������ֵ��
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
	}

	// 7. ʵ��һ����������Ϊ substractAmount() �������������ֵ��
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
	}

}
