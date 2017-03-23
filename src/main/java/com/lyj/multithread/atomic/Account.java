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

	// 实现一个方法，名为 addAmount()，来增加余额属性值。
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
	}

	// 7. 实现一个方法，名为 substractAmount() 来减少余额属性值。
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
	}

}
