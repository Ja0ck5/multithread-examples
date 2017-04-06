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
	/**
	 * 将多个大型对象插入到一个有界缓存中，然后再将它们移除
	 * 第2个堆快照中的内存用量应该与第一个对快照中的内存用量基本相同。
	 * 然而如果 doExtract 忘记将返回元素的索引置空(items[i]=null)
	 * 那么两次快照的内存用量明显不同，这就是为什么需要显示地将变量置空的情况。
	 * 但在大多数情况下，这种情况不仅不会打来帮助，反而会带来负面作用。
	 * @throws InterruptedException
	 */
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
