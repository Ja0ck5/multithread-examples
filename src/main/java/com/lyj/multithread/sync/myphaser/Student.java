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
		// 11. ���ȣ�����дһ����Ϣ���ٿ�̨����ѧ�����￼�������� phaser �� arriveAndAwaitAdvance()
		// �������ȴ������߳��ǡ�
		System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();

		// 12. Ȼ��д��Ϣ���ٿ�̨������˽�� doExercise1() ����ģ���һ�����飬д��һ����Ϣ���ٿ�̨������ phaser
		// �� arriveAndAwaitAdvance() �������ȴ�����ѧ��������һ�����顣
		System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise1();
		System.out.printf("%s: Has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();

		// 13. Ϊ�ڶ����͵�����ʵ����ͬ�Ĵ��롣
		System.out.printf("%s: Is going to do the second exercise.%s\n", Thread.currentThread().getName(), new Date());
		doExercise2();
		System.out.printf("%s: Has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
		System.out.printf("%s: Is going to do the third exercise. %s\n", Thread.currentThread().getName(), new Date());
		doExercise3();
		System.out.printf("%s: Has finished the exam. %s\n", Thread.currentThread().getName(), new Date());
		phaser.arriveAndAwaitAdvance();
	}

	// 14. ʵ�ָ������� doExercise1()���˷������߳��������һ��ʱ�䡣
	private void doExercise1() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 15. ʵ�ָ������� doExercise2()���˷������߳��������һ��ʱ�䡣
	private void doExercise2() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 16. ʵ�ָ������� doExercise3()���˷������߳��������һ��ʱ�䡣
	private void doExercise3() {
		try {
			long duration = (long) (Math.random() * 10);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
