package com.lyj.custom;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

	protected GrumpyBoundedBuffer(int capacity) {
		super(capacity);
	}
	
	/**
	 * 缓存已经满了，并不是有界缓存的一个异常条件，就相当于“红灯”并不表示交通信号灯出现异常。
	 * 在实现缓存时得到的简化(使调用者管理状依赖性)并不能抵消在使用时存在的复杂性，因为现在
	 * 调用者必须做好捕获异常的准备，并且在每次缓存操作时都需要重试
	 * @param v
	 * @throws Exception
	 */
	public synchronized void put(V v) throws Exception{
		if(isFull())//先检查
			throw new RuntimeException("BufferFullException");
		doPut(v);//再运行
	}

	public synchronized V take() throws Exception{
		if(isFull())
			throw new RuntimeException("BufferFullException");
		return doTake();
	}
	
	/**
	 * 调用者必须自行处理前提条件失败的情况
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		GrumpyBoundedBuffer<Object> buffer = new GrumpyBoundedBuffer<>(10);
		//调用者必须做好捕获异常的准备，并且在每次缓存操作时都需要重试
		while(true){
			try{
				Object item = buffer.take();
				//opt item
				break;
			}catch(Exception e){
				Thread.sleep(5000);
			}
		}
	}

}
