package com.lyj.multithread.sync.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 2个线程(生产者和消费者线程)都是在Exchanger里并交换了数据类型，所以当消费者从exchange()
 * 方法返回时，它有10个字符串在buffer内。当生产者从 exchange() 方法返回时，它有空白的buffer来重新写入
 * 
 * @author Ja0ck5
 *
 */
public class Core {
	public static void main(String[] args) {

		// 16. 创建2个buffers。分别给producer和consumer使用.
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();

		// 17. 创建Exchanger对象，用来同步producer和consumer。
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

		// 18. 创建Producer对象和Consumer对象。
		MyProducer producer = new MyProducer(buffer1, exchanger);
		MyConsumer consumer = new MyConsumer(buffer2, exchanger);

		// 19. 创建线程来执行producer和consumer并开始线程。
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}
}
