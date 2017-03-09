package com.lyj.multithread.sync.all2all;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();

		List<MyTask> taskList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			MyTask task = new MyTask(i + "");
			taskList.add(task);
		}

		List<Future<Result>> resultList = null;

		try {
			/*
			 * invokeAll()方法等待它们的完成。这个方法接收Callable对象列表和返回
			 * Future对象列表。这个列表将会有列表中每个任务的一个Future对象。
			 * Future对象列表的第一个对象是Callable对象列表控制的第一个任务，以此类推。
			 */
			resultList = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		System.out.println("Main: Printing the results");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Result> future = resultList.get(i);
			try {
				Result result = future.get();
				System.out.println(result.getName() + ": " + result.getVal());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

	}
}
