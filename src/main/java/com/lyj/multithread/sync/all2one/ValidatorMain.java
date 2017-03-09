package com.lyj.multithread.sync.all2one;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ValidatorMain {

	public static void main(String[] args) {
		String username = "test";
		String password = "test";

		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");

		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

		// 创建TaskValidator队列，添加两个已创建的对象（ldapTask和dbTask）
		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);

		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		String result;
		/*
		 * 调用executor对象的invokeAny()方法。该方法接收taskList参数，返回String类型。
		 *
		 * ThreadPoolExecutor类中的invokeAny()方法接收任务数列，并启动它们，返回完成时没有抛出异常的第一个
		 * 任务的结果。该方法返回的数据类型与启动任务的call()方法返回的类型一样。在本例中，它返回String值。
		 */
		try {
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");

	}

}
