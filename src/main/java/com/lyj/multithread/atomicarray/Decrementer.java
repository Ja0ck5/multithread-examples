package com.lyj.multithread.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

//5.����һ���࣬��Ϊ Decrementer����ʵ�� Runnable �ӿڡ�
public class Decrementer implements Runnable {

	// 6.����һ��˽�� AtomicIntegerArray ���ԣ���Ϊ vector����������һ������ array��
	private AtomicIntegerArray vector;

	// 7.ʵ����Ĺ��캯������ʼ����������ֵ��
	public Decrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}

	// 8.ʵ�� run() ������ʹ�� getAndDecrement() ��������array�������Ԫ�ء�
	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndDecrement(i);
		}
	}

}