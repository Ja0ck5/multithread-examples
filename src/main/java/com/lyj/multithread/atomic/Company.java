package com.lyj.multithread.atomic;

public class Company implements Runnable {

	// 声明一个私有 Account 属性，名为 account。
	private Account account;

	public Company(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			account.addAmount(1000);
		}
	}

}
