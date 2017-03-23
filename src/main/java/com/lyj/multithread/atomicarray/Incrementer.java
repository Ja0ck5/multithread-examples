package com.lyj.multithread.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

//1.����һ���࣬��Ϊ Incrementer����ʵ�� Runnable �ӿڡ�
public class Incrementer implements Runnable {

	// 2.����һ��˽�� AtomicIntegerArray ���ԣ���Ϊ vector����������һ������ array��
	private AtomicIntegerArray vector;

	// 3.ʵ����Ĺ��캯������ʼ����������ֵ��
	public Incrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}

	// 4.ʵ�� run() ������ʹ�� getAndIncrement() ������array�������Ԫ�ء�
	@Override
	public void run() {
		for (int i = 0; i < vector.length(); i++) {
			vector.getAndIncrement(i);
		}
	}

}