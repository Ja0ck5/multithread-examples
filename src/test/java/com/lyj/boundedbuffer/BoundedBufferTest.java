package com.lyj.boundedbuffer;


import junit.framework.TestCase;

public class BoundedBufferTest extends TestCase {

	public void testIsEmptyWhenConstructed() {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	public void testIsFullAfterPuts() throws InterruptedException {
		BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		for (int i = 0; i < 10; i++) {
			bb.put(i);
		}
		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}

	public void testBlocksWhenEmpty() throws InterruptedException {
		final BoundedBuffer<Integer> bb = new BoundedBuffer<Integer>(10);
		Thread taker = new Thread(){
			@Override
			public void run() {
				try {
					Integer unused = bb.take();
					fail();//执行到这里，意味�?没有阻塞，表示出现错�?
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		try {
			taker.start();
			Thread.sleep(5000);
			taker.interrupt();
			taker.join(5000);
			assertFalse(taker.isAlive());
		} catch (Exception e) {
			fail();
		}
		
	}

	class Big{
		double[] data = new double[100000];
	}
	private static final int CAPACITY = 1000;
	void testLeak() throws InterruptedException{
		BoundedBuffer<Big> bb = new BoundedBuffer<Big>(CAPACITY);
//		int heapSize1 = /*生成堆快照*/
		for (int i = 0; i < CAPACITY; i++) {
			bb.put(new Big());
		}
		for (int i = 0; i < CAPACITY; i++) {
			bb.take();
		}
//		int heapSize2 = /*生成堆快照*/
//		assertTrue(Math.abs(heapSize1-heapSize2) < THRESHOLD);
	}
}
