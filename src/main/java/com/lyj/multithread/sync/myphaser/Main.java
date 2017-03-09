package com.lyj.multithread.sync.myphaser;

/**
 * �ڽ׶θı�֮ǰ���ڻ��� arriveAndAwaitAdvance() ���������ߵ�ȫ���߳���֮ǰ���˷����� phaser
 * ���á�����������յ�ǰ�׶�����Ϊ������0�ǵ�һ��phase ������ע��Ĳ��������������õĲ�����actual
 * phase�������Ҫ���ڲ�ͬ�ĵ�ǰ�׶�ִ�в�ͬ�Ĳ�������ô�����ʹ��ѡ���Խṹ��if/else �� switch����ѡ������ִ�еĲ��������������ʹ����
 * switch �ṹ��Ϊÿ��phase�ĸı�ѡ��ͬ�ķ�����
 * 
 * onAdvance() �������� Boolean ֵ���� phaser �ս����������� false
 * ֵ����ʾ����û���սᣬ��ô�߳̽�����ִ������phases�����phaser
 * ������ֵ����ôphaser������ȫ���������߳��ǣ�����ת��phaser��terminated
 * ״̬������֮����κζ�phaser�ķ����ĵ��ö��ᱻ���̷��أ�����isTerminated() ������������ֵ��
 * 
 * �ں����࣬���㴴�� MyPhaser ������phaser���㲻�ñ�ʾ�����ߵ���������Ϊÿ�� Student ��������� register()
 * ����������phaser�Ĳ����ߵ�ע�ᡣ������ò�����Student �������ִ�������߳���phaser֮����������κι�ϵ��
 * ˵��ģ�phaser�Ĳ����������Ǹ����ֶ��ѡ�phaser�������֮��û���κι�ϵ��
 * 
 * @author Ja0ck5
 *
 */
// 17. ʵ�����ӵ�main�࣬������ΪMain���ಢ���main() ������
public class Main {

	public static void main(String[] args) {

		// 18. ���� MyPhaser����
		MyPhaser phaser = new MyPhaser();

		// 19. ����5�� Student ����ʹ��register()������phaser��ע�����ǡ�
		Student students[] = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}

		// 20. ����5���߳�������students����ʼ���ǡ�
		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}

		// 21. �ȴ�5���̵߳��սᡣ
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 22. ����isTerminated()������дһ����Ϣ����phaser����termination״̬��
		System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
	}
}