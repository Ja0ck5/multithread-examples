package com.lyj.multithread.sync.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		// ����һ���µ�Task�����������²�Ʒ�����еĲ�Ʒ��first����ʹ��ֵ0��last����ʹ��ֵ10000����Ʒ���еĴ�С��
		Task task = new Task(products, 0, products.size(), 0.20);

		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);

		// ��ʾ����ÿ��5������еı仯��Ϣ�Ĵ���顣�����е�һЩ����ֵд�뵽����̨��ֱ�������������ִ��
		do {
			System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		// �رճ�
		pool.shutdown();

		// ʹ��isCompletedNormally()�����������������ʱû�г�������������£�д��һ����Ϣ������̨��
		if (task.isCompletedNormally()) {
			System.out.printf("Main: The process has completed normally.\n");
		}

		// ������֮�����в�Ʒ�ļ۸�Ӧ����12�����۸���12�����в�Ʒ�����ƺͼ۸�д�뵽����̨������������Ǵ�����������ǵļ۸�
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
			}
		}
		//д��һ����Ϣ������̨��������Ľ�����
		System.out.println("Main: End of the program.\n");

	}

}
