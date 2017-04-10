package com.lyj.custom;

/**
 * 实现了明晰地状态依赖性管理
 * ConditionBoudedBuffer 能使用单一通知方法而不是 notifyAll ，效率会更高
 * @author Ja0ck5
 *
 * @param <V>
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected BoundedBuffer(int capacity) {
		super(capacity);
	}
	
	public synchronized void Put(V v) throws InterruptedException{
		while(isFull())
			wait();
		doPut(v);
		notifyAll();
	}

	public synchronized V take() throws InterruptedException{
		while(isEmpty())
			wait();
		V v = doTake();
		notifyAll();
		return v;
	}

}
