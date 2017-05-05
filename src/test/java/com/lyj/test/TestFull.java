package com.lyj.test;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestFull {
	
	static BlockingQueue<Runnable> blockingQueue= new LinkedBlockingQueue<>(2);
	static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1000, TimeUnit.SECONDS, blockingQueue);
	static ThreadPoolExecutor threadCallerRunExecutor = new ThreadPoolExecutor(2, 5, 1000, TimeUnit.SECONDS, blockingQueue,new ThreadPoolExecutor.CallerRunsPolicy());
	
	/*@Test
	public void testFull(){
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			threadPool.execute(new FullRunnable());
		}
		
		threadPool.shutdown();
	}*/
	
	public static void main(String[] args) {
		
//		fixedPool();
//		fixedQueuePool();
//		callerRunsPool();
		catchInQueuePool();
	}

	/**
	 * 
	 */
	public static void catchInQueuePool() {
		Queue<Runnable> queue = new LinkedBlockingQueue<>(100);
		FullRunnable fr = new FullRunnable();
			try {
				for (int i = 0; i < EXCEPTION_SIZE * 2; i++) {
					try {
						threadPoolExecutor.execute(fr);
					} catch (Exception e) {
						//丢到 queue
						if(!queue.offer(fr)) throw new RuntimeException("!!! too many worked runnables");
					}
				}
				//取出 queue 并执行
				System.out.println("in catching...... ");
				/*for (Iterator<Runnable> iterator = queue.iterator(); iterator.hasNext();) {
					Runnable runnable = iterator.next();
					if (!threadPoolExecutor.isShutdown()) {
						new Thread(runnable).start();
					}
				}*/
				for (Runnable runnable : queue) {
					if (!threadPoolExecutor.isShutdown()) {
						new Thread(runnable).start();
					}
				}
			}finally{
				threadPoolExecutor.shutdown();
			}
	}

	/**
	 * 
	 */
	public static void callerRunsPool() {
		for (int i = 0; i < EXCEPTION_SIZE; i++) {
			threadCallerRunExecutor.execute(new FullRunnable());
		}
		threadCallerRunExecutor.shutdown();
	}
	
	/**
	 * 
	 */
	public static void fixedPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			threadPool.execute(new FullRunnable());
		}
		
		threadPool.shutdown();
	}

	private static final int EXCEPTION_SIZE = 8;
	/**
	 * 
	 */
	public static void fixedQueuePool() {
		//ask com.lyj.test.FullRunnable@55f96302 rejected from java.util.concurrent.ThreadPoolExecutor@3d4eac69[Running, pool size = 5, active threads = 5, queued tasks = 2, completed tasks = 0]
		for (int i = 0; i < EXCEPTION_SIZE; i++) {
			threadPoolExecutor.execute(new FullRunnable());
		}
		threadPoolExecutor.shutdown();
	}

}
