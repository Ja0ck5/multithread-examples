package com.lyj.multithread.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

	public static void main(String[] args) {
		Account account = new Account();
		account.setBalance(new AtomicLong(1000));

		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		// 创建一个新的 Bank t任务和一个线程运行它。
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);

		//在操控台写上账号的初始余额。
		System.out.println("Account : Initial Balance:" + account.getBalance());

		//开始线程。
		companyThread.start();
		bankThread.start();

		//使用 join() 方法等待线程的完结并把账号最终余额写入操控台。
		try {
			companyThread.join();
			bankThread.join();
			System.out.println("Account : Final Balance: "+account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
