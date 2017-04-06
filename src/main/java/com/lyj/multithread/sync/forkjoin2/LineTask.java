package com.lyj.multithread.sync.forkjoin2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class LineTask extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String line[];
	private int start, end;
	private String word;

	public LineTask(String line[], int start, int end, String word) {
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		Integer result = null;
		// 如果属性end和start之差小于100，这个任务在行中由start和end属性使用count()方法决定的片断中查找单词
		if (end - start < 100) {
			result = count(line, start, end, word);
		} else {
			// 行中的单词组分成两部分，创建两个新的LineTask对象来处理这两个组，在池中使用invokeAll()方法执行它们
			int mid = (start + end) >>> 1;
			LineTask task1 = new LineTask(line, start, mid, word);
			LineTask task2 = new LineTask(line, mid, end, word);
			invokeAll(task1, task2);

			try {
				result = groupResults(task1.get(), task2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Integer count(String[] line, int start, int end, String word) {
		int counter = 0;
		for (int i = start; i < end; i++) {
			if (line[i].equals(word)) {
				counter++;
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return counter;
	}

	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		result = number1 + number2;
		return result;
	}
	
	
}
