package com.lyj.custom;

import org.omg.Messaging.SyncScopeHelper;

public class BaseBoundedBuffer<V> {
	//底层状态对子类隐藏 且 均由内置锁来保护
	private final V[] buf;
	private int  tail;
	private int  head;
	private int  count;
	
	protected BaseBoundedBuffer(int capacity) {
		this.buf = (V[]) new Object[capacity];
	}
	
	protected synchronized final void doPut(V v){
		buf[tail] = v;
		if(++tail == buf.length){
			tail = 0;
		}
		++count;
	}

	protected synchronized final V doTake(){
		V v = buf[head];
		if(++head == buf.length){
			head = 0;
		}
		--count;
		return v;
	}
	
	public synchronized final boolean isFull(){
		return count == buf.length;
	}

	public synchronized final boolean isEmpty(){
		return count == 0;
	}

}
