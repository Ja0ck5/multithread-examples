package com.lyj.multithread.sync.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class MyProducer implements Runnable{
	// 2. ���� List<String>������Ϊ buffer�����ǵȵ�Ҫ���໥�������������͡�
	private List<String> buffer;
	
	// 3. ���� Exchanger<List<String>>; ������Ϊexchanger����� exchanger ����������ͬ��producer��consumer�ġ�
	private Exchanger<List<String>> exchanger;
	
	public MyProducer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}



	@Override
	public void run() {// 5. ʵ�� run() ����. �ڷ����ڣ�ʵ��10�ν�����
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("MyProducer : %d\n",cycle);
			for (int j = 0; j < 10; j++) {// 6. ��ÿ��ѭ���У���10���ַ�����buffer��
				String message = "Event" + ((i*10)+j);
				System.out.printf("MyProducer : %s\n",message);
				buffer.add(message);
			}
			// 7. ���� exchange() ��������consumer�������ݡ��˷������ܻ��׳�InterruptedException �쳣, ���ϴ�����롣
			try {
				buffer = exchanger.exchange(buffer);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("MyProducer : buffer size : %d\n",buffer.size());
			cycle++;
		}
	}

}
