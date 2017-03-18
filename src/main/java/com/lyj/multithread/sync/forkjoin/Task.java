package com.lyj.multithread.sync.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
	private static final long serialVersionUID = 1L;

	private List<Product> products;
	// ��������˽�еġ�int���͵�����first��last����Щ���Խ�������������Ʒ����������
	private int first;
	private int last;

	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		// ���last��first�Ĳ�С��10������ֻ�ܸ��¼۸�С��10�Ĳ�Ʒ����ʹ��updatePrices()�������������ò�Ʒ�ļ۸�
		if (last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first) / 2;
			System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			/**
			 * ִ��ÿ������������������������һ��ͬ�����ã���������ڼ�����������ɣ�����ִ��֮ǰ��
			 * ����ȴ�������Ľ��������������ڵȴ����������񣨽�����ʱ������ִ�����Ĺ����߳�ִ���������ڵȴ�������
			 * ��������Ϊ�£�Fork/Join��ܱ�Runnable��Callable�������ṩһ�ָ���Ч���������
			 */
			invokeAll(t1, t2);
		}
	}

	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

}
