package com.lyj.multithread.sync.myphaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable {

	private Phaser phaser;

	public Student(Phaser phaser) {
		super();
		this.phaser = phaser;
	}

	@Override
	public void run() {
		// 11. 首先，方法写一条信息到操控台表明学生到达考场并调用 phaser 的 arriveAndAwaitAdvance()
		// 方法来等待其他线程们。
		System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();

		// 12. 然后，写信息到操控台，调用私有 doExercise1() 方法模拟第一场测验，写另一条信息到操控台并调用 phaser
		// 的 arriveAndAwaitAdvance() 方法来等待其他学生结束第一场测验。
		System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise1();
		System.out.printf("%s: Has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();

		// 13. 为第二场和第三场实现相同的代码。
		System.out.printf("%s: Is going to do the second exercise.%s\n", Thread.currentThread().getName(), new Date());
		doExercise2();
		System.out.printf("%s: Has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
		System.out.printf("%s: Is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise3();
		System.out.printf("%s: Has finished the exam. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
	}

	// 14. 实现辅助方法 doExercise1()。此方法让线程随机休眠一段时间。
	private void doExercise1() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 15. 实现辅助方法 doExercise2()。此方法让线程随机休眠一段时间。
	private void doExercise2() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 16. 实现辅助方法 doExercise3()。此方法让线程随机休眠一段时间。
	private void doExercise3() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
