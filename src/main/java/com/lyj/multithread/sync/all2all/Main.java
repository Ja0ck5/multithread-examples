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
			 * invokeAll()�����ȴ����ǵ���ɡ������������Callable�����б�ͷ���
			 * Future�����б�����б������б���ÿ�������һ��Future����
			 * Future�����б�ĵ�һ��������Callable�����б���Ƶĵ�һ�������Դ����ơ�
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
