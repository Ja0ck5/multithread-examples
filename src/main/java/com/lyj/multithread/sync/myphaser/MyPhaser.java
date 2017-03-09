package com.lyj.multithread.sync.myphaser;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

	/**
	 * onAdvance() 方法返回 Boolean 值表明 phaser 终结与否。
	 * 
	 * 如果返回 false值，表示它还没有终结，那么线程将继续执行其他phases。
	 * 
	 * 如果phaser返回真值，那么phaser将叫醒全部待定的线程们，并且转移phaser到terminated 状态，
	 * 
	 * 所以之后的任何对phaser的方法的调用都会被立刻返回，还有isTerminated() 方法将返回真值。
	 */
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
		case 0:
			return studentsArrived();
		case 1:
			return finishFirstExercise();
		case 2:
			return finishSecondExercise();
		case 3:
			return finishExam();
		default:
			return true;
		}
	}

	// 3. 实现辅助方法 studentsArrived()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
	private boolean studentsArrived() {
		System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
		System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
		return false;
	}

	// 4. 实现辅助方法 finishFirstExercise()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
	private boolean finishFirstExercise() {
		System.out.printf("Phaser: All the students have finished the first exercise.\n");
		System.out.printf("Phaser: It's time for the second one.\n");
		return false;
	}

	// 5. 实现辅助方法 finishSecondExercise()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
	private boolean finishSecondExercise() {
		System.out.printf("Phaser: All the students have finished the second exercise.\n");
		System.out.printf("Phaser: It's time for the third one.\n");
		return false;
	}

	// 6. 实现辅助方法 finishExam()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
	private boolean finishExam() {
		System.out.printf("Phaser: All the students have finished the exam.\n");
		System.out.printf("Phaser: Thank you for your time.\n");
		return true;
	}

}
