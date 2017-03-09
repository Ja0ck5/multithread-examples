package com.lyj.multithread.sync.condition;

/**
 * 一个锁可能伴随着多个条件。这些条件声明在Condition接口中。 这些条件的目的是允许线程拥有锁的控制并且检查条件是否为true，
 * 如果是false，那么线程将被阻塞，直到其他线程唤醒它们。
 * 
 * Condition接口提供一种机制，阻塞一个线程和唤醒一个被阻塞的线程。
 * 
 * @author Ja0ck5
 *
 */
public class FileMock {
	/**
	 * 存储文件内容
	 */
	private String content[];
	/**
	 * 被检索到的模拟文件的行数
	 */
	private int index;

	public FileMock(int size, int length) {
		content = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder buffer = new StringBuilder(length);
			for (int j = 0; j < length; j++) {
				int indice = (int) Math.random() * 255;
				buffer.append((char) indice);
			}
			content[i] = buffer.toString();// 随机字符初始化文件的内容
		}
		index = 0;
	}

	/**
	 * 如果文件有更多的行来处理，则返回true， 如果我们已经取到了模拟文件的尾部，则返回false
	 *
	 * @return
	 */
	public boolean hasMoreLines() {
		return index < content.length;
	}

	/**
	 * 返回index属性所确定的行数并增加其值
	 * @return
	 */
	public String getLine() {
		if (this.hasMoreLines()) {
			System.out.println("Mock: " + (content.length - index));
			return content[index++];
		}
		return null;
	}
	
	

}
