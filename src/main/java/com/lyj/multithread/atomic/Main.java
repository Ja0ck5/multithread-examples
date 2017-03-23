package com.lyj.multithread.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

	public static void main(String[] args) {
		Account account = new Account();
		account.setBalance(new AtomicLong(1000));

		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		// ����һ���µ� Bank t�����һ���߳���������
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);

		//�ڲٿ�̨д���˺ŵĳ�ʼ��
		System.out.println("Account : Initial Balance:" + account.getBalance());

		//��ʼ�̡߳�
		companyThread.start();
		bankThread.start();

		//ʹ�� join() �����ȴ��̵߳���Ტ���˺��������д��ٿ�̨��
		try {
			companyThread.join();
			bankThread.join();
			System.out.println("Account : Final Balance: "+account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
