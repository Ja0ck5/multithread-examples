package com.lyj.multithread.sync.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
	private static final long serialVersionUID = 1L;

	private List<Product> products;
	// 声明两个私有的、int类型的属性first和last。这些属性将决定这个任务产品的阻塞过程
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
		// 如果last和first的差小于10（任务只能更新价格小于10的产品），使用updatePrices()方法递增的设置产品的价格。
		if (last - first < 10) {
			updatePrices();
		} else {
			int middle = (last + first) / 2;
			System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			/**
			 * 执行每个任务所创建的子任务。这是一个同步调用，这个任务在继续（可能完成）它的执行之前，
			 * 必须等待子任务的结束。当任务正在等待它的子任务（结束）时，正在执行它的工作线程执行其他正在等待的任务。
			 * 在这种行为下，Fork/Join框架比Runnable和Callable对象本身提供一种更高效的任务管理。
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
