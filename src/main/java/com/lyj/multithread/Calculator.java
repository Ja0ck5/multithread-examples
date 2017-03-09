package com.lyj.multithread;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Calculator implements Runnable {

	private int number;

	public Calculator(int number) {
		this.number = number;
	}

	public void run() {
		for (int i = 0; i < 10; i++)
			System.out.printf("%s: %d * %d = %d\n ", Thread.currentThread().getName(), number, i, i * number);
	}

	public static void main(String[] args) {
		// create threads
		/*
		 * for (int i = 0; i < 10; i++) { Calculator calculator = new
		 * Calculator(i); Thread thread = new Thread(calculator);
		 * thread.start(); }
		 */

		Thread[] threads = new Thread[10];
		Thread.State[] status = new Thread.State[10];

		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if (i % 2 == 0)
				threads[i].setPriority(Thread.MAX_PRIORITY);
			else
				threads[i].setPriority(Thread.MIN_PRIORITY);
			threads[i].setName("thread-" + i);
		}

		try (FileWriter file = new FileWriter("C:\\Users\\Ja0ck5\\Desktop\\20170220\\log.txt");
				PrintWriter pw = new PrintWriter(file);) {
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Statu of Thread  " + i + ":" + threads[i].getState());
				status[i] = threads[i].getState();
			}

			// start
			for (int i = 0; i < 10; i++)
				threads[i].start();
			boolean finish = false;
			// 一直检查它们的状态。如果发现它的状态改变，就把状态记入文本
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}

				finish = true;
				for (int i = 0; i < 10; i++)
					finish = finish && (threads[i].getState() == State.TERMINATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 实现方法写线程的 ID, name, priority, old status, 和 new status。
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}

}
