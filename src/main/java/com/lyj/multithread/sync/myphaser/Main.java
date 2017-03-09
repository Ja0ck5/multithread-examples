package com.lyj.multithread.sync.myphaser;

/**
 * 在阶段改变之前和在唤醒 arriveAndAwaitAdvance() 方法中休眠的全部线程们之前，此方法被 phaser
 * 调用。这个方法接收当前阶段数作为参数，0是第一个phase ，还有注册的参与者数。最有用的参数是actual
 * phase。如果你要基于不同的当前阶段执行不同的操作，那么你必须使用选择性结构（if/else 或 switch）来选择你想执行的操作。例子里，我们使用了
 * switch 结构来为每个phase的改变选择不同的方法。
 * 
 * onAdvance() 方法返回 Boolean 值表明 phaser 终结与否。如果返回 false
 * 值，表示它还没有终结，那么线程将继续执行其他phases。如果phaser
 * 返回真值，那么phaser将叫醒全部待定的线程们，并且转移phaser到terminated
 * 状态，所以之后的任何对phaser的方法的调用都会被立刻返回，还有isTerminated() 方法将返回真值。
 * 
 * 在核心类，当你创建 MyPhaser 对象，在phaser中你不用表示参与者的数量。你为每个 Student 对象调用了 register()
 * 方法创建了phaser的参与者的注册。这个调用不会在Student 对象或者执行它的线程与phaser之间这个建立任何关系。
 * 说真的，phaser的参与者数就是个数字而已。phaser与参与者之间没有任何关系。
 * 
 * @author Ja0ck5
 *
 */
// 17. 实现例子的main类，创建名为Main的类并添加main() 方法。
public class Main {

	public static void main(String[] args) {

		// 18. 创建 MyPhaser对象。
		MyPhaser phaser = new MyPhaser();

		// 19. 创建5个 Student 对象并使用register()方法在phaser中注册他们。
		Student students[] = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}

		// 20. 创建5个线程来运行students并开始它们。
		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}

		// 21. 等待5个线程的终结。
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 22. 调用isTerminated()方法来写一条信息表明phaser是在termination状态。
		System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
	}
}