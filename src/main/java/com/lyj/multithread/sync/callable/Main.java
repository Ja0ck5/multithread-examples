package com.lyj.multithread.sync.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}

		do {
			// getCompletedTaskNumber()方法获得的已完成的任务数
			System.out.printf("Main: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());
			// 对于数列中的10个Future对象，使用isDone()方法，将信息写入（到控制台）表明它们所管理的任务是否已经完成
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}

			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (executor.getCompletedTaskCount() < resultList.size());

		System.out.printf("Main: Results\n");
		//每个Future对象，通过它的任务使用get()方法获取返回的Integer对象
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);

			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Main: Task %d: %d\n", i, number);
		}
		executor.shutdown();
	}

}
