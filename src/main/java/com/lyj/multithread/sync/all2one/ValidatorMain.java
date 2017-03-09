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

		// ����TaskValidator���У���������Ѵ����Ķ���ldapTask��dbTask��
		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);

		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		String result;
		/*
		 * ����executor�����invokeAny()�������÷�������taskList����������String���͡�
		 *
		 * ThreadPoolExecutor���е�invokeAny()���������������У����������ǣ��������ʱû���׳��쳣�ĵ�һ��
		 * ����Ľ�����÷������ص��������������������call()�������ص�����һ�����ڱ����У�������Stringֵ��
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
