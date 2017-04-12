package com.lyj.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import org.junit.runner.notification.RunListener.ThreadSafe;
/**
 * AQS 的状态 用来表示闭锁的状态
 * 0 关闭
 * 1 打开
 * @author Ja0ck5
 *
 */
@ThreadSafe
public class OneShotLatch {
	
	private final Sync sync = new Sync();
	
	public void signal(){
		sync.releaseShared(0);
	}
	
	public void await() throws InterruptedException{
		sync.acquireSharedInterruptibly(1);
	}
	
	private class Sync extends AbstractQueuedSynchronizer{
		protected int tryAcquireShared(int ignored){
			//如果闭锁是开的 state == 1 ，那么这个操作将成功，否则失败
			return (getState() == 1) ? 1 : -1; 
		}
		
		protected boolean tryReleaseShared(int ignored){
			setState(1);//打开闭锁
			return true;
		}
	}
	
	
	
	
}
