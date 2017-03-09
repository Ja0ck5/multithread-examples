package com.lyj.multithread.sync.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 2���߳�(�����ߺ��������߳�)������Exchanger�ﲢ�������������ͣ����Ե������ߴ�exchange()
 * ��������ʱ������10���ַ�����buffer�ڡ��������ߴ� exchange() ��������ʱ�����пհ׵�buffer������д��
 * 
 * @author Ja0ck5
 *
 */
public class Core {
	public static void main(String[] args) {

		// 16. ����2��buffers���ֱ��producer��consumerʹ��.
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();

		// 17. ����Exchanger��������ͬ��producer��consumer��
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

		// 18. ����Producer�����Consumer����
		MyProducer producer = new MyProducer(buffer1, exchanger);
		MyConsumer consumer = new MyConsumer(buffer2, exchanger);

		// 19. �����߳���ִ��producer��consumer����ʼ�̡߳�
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}
}
