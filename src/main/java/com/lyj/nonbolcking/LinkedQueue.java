package com.lyj.nonbolcking;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {
	
	private static class Node<E>{
		final E item;
		final AtomicReference<Node<E>> next;
		public Node(E item, AtomicReference<Node<E>> next) {
			super();
			this.item = item;
			this.next = next;
		}
	}
	
	private final Node<E> dummy = new Node<E>(null,null);
	/**
	 * 空队列通常包含一个“哨兵节点(setinel)” 或者 “哑节点(dummy)”
	 * 并且头结点和尾节点在初始化的时候都指向该节点
	 */
	private final AtomicReference<Node<E>> head = new AtomicReference<LinkedQueue.Node<E>>(dummy);
	private final AtomicReference<Node<E>> tail = new AtomicReference<LinkedQueue.Node<E>>(dummy);
	
	public boolean put(E item){
		Node<E> newNode = new Node<>(item, null);
		while(true){
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();
			if(curTail == tail.get()){
				if(tailNext != null){
					//队列处于中间状态，推进尾节点
					tail.compareAndSet(curTail, tailNext);
				}else{
					//队列处于稳定状态，尝试插入新节点
					if(curTail.next.compareAndSet(null, newNode)){
						//插入操作成功，尝试推进尾节点
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				}
			}
		}
	}
	

}
