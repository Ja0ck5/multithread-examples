package com.lyj.multithread.sync.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class MyProducer implements Runnable{
	// 2. 声明 List<String>对象，名为 buffer。这是等等要被相互交换的数据类型。
	private List<String> buffer;
	
	// 3. 声明 Exchanger<List<String>>; 对象，名为exchanger。这个 exchanger 对象是用来同步producer和consumer的。
	private Exchanger<List<String>> exchanger;
	
	public MyProducer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}



	@Override
	public void run() {// 5. 实现 run() 方法. 在方法内，实现10次交换。
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("MyProducer : %d\n",cycle);
			for (int j = 0; j < 10; j++) {// 6. 在每次循环中，加10个字符串到buffer。
				String message = "Event" + ((i*10)+j);
				System.out.printf("MyProducer : %s\n",message);
				buffer.add(message);
			}
			// 7. 调用 exchange() 方法来与consumer交换数据。此方法可能会抛出InterruptedException 异常, 加上处理代码。
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
