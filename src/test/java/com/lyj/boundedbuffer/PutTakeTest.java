package com.lyj.boundedbuffer;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 在初始化 CyclicBarrier 时将计数值指定为工作者线程数量再加一(测试线程)，并在运行开始和结束时，
 * 使工作者线程和测试线程都在这个栅栏处等待。这能确保所有线程在开始执行任何工作之前，都首先执行到同一个
 * 位置。
 * PutTakeTest 使用这项技术来协调工作者线程的启动和停止。从而产生更多的 并发交替操作。
 * 我们仍然 无法确保调度器不会采用串行的方式来执行们每个线程，但只要这些线程的执行时间足够长，
 * 就能降低调度机制对结果的不利影响。
 * @author Ja0ck5
 *
 */
public class PutTakeTest {

	private static final ExecutorService pool = Executors.newCachedThreadPool();

	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;
	private final BoundedBuffer<Integer> bb;
	private final int nTrials, nPairs;

	public PutTakeTest(int capacity, int nTrials, int nPairs) {
		this.bb = new BoundedBuffer<Integer>(capacity);
		this.nTrials = nTrials;
		this.nPairs = nPairs;
		this.barrier = new CyclicBarrier(nPairs * 2 + 1);
	}

	void test() {
		try {
			for (int i = 0; i < nPairs; i++) {
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			barrier.await();// 等待所有线程就绪
			System.out.println("-------------all threads start------------");
			barrier.await();// 等待所有线程执行完成
			System.out.println("-------------all threads end------------");
			assertEquals(putSum.get(), takeSum.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	class Producer implements Runnable {

		@Override
		public void run() {
			try {
				int seed = this.hashCode() ^ (int) System.nanoTime();
				int sum = 0;
				barrier.await();
				for (int i = 0; i < nTrials; i++) {
					bb.put(seed);
					sum+=seed;
					seed = xorShift(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}


	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			try{
				barrier.await();
				int sum = 0;
				for (int i = nTrials; i >0; i--) {
					sum += bb.take();
				}
				takeSum.getAndAdd(sum);
				barrier.await();
			}catch(Exception e){
				throw new RuntimeException(e);
			}
			
		}

	}

	private int xorShift(int y) {
		y^=(y<<6);
		y^=(y>>>21);
		y^=(y<<7);
		return y;
	}
	
	public static void main(String[] args) {
		new PutTakeTest(10, 10, 1000).test();
		pool.shutdown();
	}
}
