package com.lyj.multithread.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

//9.���Ǵ���һ��ʾ��������ʾ��������һ���࣬��Ϊ Main ����� main()������
public class Main {
	public static void main(String[] args) {

		// 10.����һ����������Ϊ THREADS����������ֵΪ 100������һ����1��000��Ԫ�ص� AtomicIntegerArray ����
		final int THREADS = 100;
		AtomicIntegerArray vector = new AtomicIntegerArray(1000);

		// 11. ����һ�� Incrementer ����������֮ǰ������ԭ�� array��
		Incrementer incrementer = new Incrementer(vector);

		// 12.����һ�� Decrementer ����������֮ǰ������ԭ�� array��
		Decrementer decrementer = new Decrementer(vector);

		// 13.����2��array �ֱ�洢 100 ��Thread ����
		Thread threadIncrementer[] = new Thread[THREADS];
		Thread threadDecrementer[] = new Thread[THREADS];

		// 14.���������� 100 ���߳���ִ�� Incrementer ��������� 100 ���߳���ִ�� Decrementer
		// ���񡣰��̴߳�����֮ǰ������arrays�ڡ�
		for (int i = 0; i < THREADS; i++) {
			threadIncrementer[i] = new Thread(incrementer);
			threadDecrementer[i] = new Thread(decrementer);

			threadIncrementer[i].start();
			threadDecrementer[i].start();
		}
		// 15.ʹ�� join() �������ȴ��̵߳���ᡣ
		for (int i = 0; i < 100; i++) {
			try {
				threadIncrementer[i].join();
				threadDecrementer[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 16.��ԭ��array���0��Ԫ��д��ٿ�̨��ʹ�� get() ��������ȡԭ�� array Ԫ�ء�
		for (int i = 0; i < vector.length(); i++) {
			if (vector.get(i) != 0) {
				System.out.println("Vector[" + i + "] : " + vector.get(i));
			}
		}

		// 17.�ڲٿ�̨д����Ϣ�������ӽ�����
		System.out.println("Main: End of the example");
	}
}