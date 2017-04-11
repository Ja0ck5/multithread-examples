package com.lyj.boundedbuffer;

public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected SleepyBoundedBuffer(int capacity) {
		super(capacity);
	}
	/**
	 * 通过轮询和休眠来实现简单的阻塞
	 * @param v
	 * @throws InterruptedException
	 */
	public void put(V v) throws InterruptedException{
		while(true){
			synchronized(this){
				if(!isFull()){
					doPut(v);
					return;
				}
			}
			//当前执行的线程释放所并休眠一段时间，使其他线程能够访问当前缓存
			//如果没有释放所，在临界区内，也就是在休眠或阻塞的时候持有一个锁是不好的做法
			//因为只要线程不释放这个锁，有些条件(缓存满/空)就永远无法为真
			Thread.sleep(5000);
		}
	}

	public V take() throws InterruptedException{
		while(true){
			synchronized(this){
				if(!isEmpty()){
					return doTake();
				}
			}
			Thread.sleep(5000);
		}
	}

}
