package com.lyj.multithread;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandler implements UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		//捕获和处理线程对象抛出的未检测异常来避免程序终结
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread : %s\n",t.getId());
		System.out.printf("Exception %s : %s\n",e.getClass().getName(),e.getMessage());
		System.out.printf("Stack trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n ",t.getState());
	}
	
	

}
