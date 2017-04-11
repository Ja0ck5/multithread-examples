package com.lyj.thread.gate;
/**
 * 只支持等待打开阀门，因此它只在 open 中执行通知
 * 想要即支持 “等待打开” 又支持“等待关闭”
 * 那么 ThreadGate 必须在 open 和 close 中都进行通知
 * 很好的说明了为什么在维护状态依赖的类时是困难的：
 * 当增加一个新的状态依赖操作时，可能需要对多条修改对象的代码路径进行改动，才能正确地执行通知。
 * @author Ja0ck5
 */
public class ThreadGate {
	
	private boolean isOpen;
	private int generation;
	
	public synchronized void close(){
		isOpen = false;
	}
	
	public synchronized void open(){
		++generation;
		isOpen=true;
		notifyAll();
	}
	
	public synchronized void await() throws InterruptedException{
		int arrivalGeneration = generation;
		while(!isOpen && arrivalGeneration == generation){
			wait();
		}
	}

}
