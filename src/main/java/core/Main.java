package core;

/**
 * 普通方法使用条件队列 
 * IllegalMonitorStateException
 * @author Ja0ck5
 */
public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		try {
			//Exception in thread "main" java.lang.IllegalMonitorStateException
			main.getNa();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getNa() throws InterruptedException {
		wait();
		System.out.println("==============");
		notify();
	}

}
