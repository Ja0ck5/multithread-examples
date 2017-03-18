package com.lyj.multithread.sync.forkjoin3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderProcessor extends RecursiveTask<List<String>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String path;
	private String extension;

	public FolderProcessor(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}

	@Override
	protected List<String> compute() {
		// 用来保存存储在文件夹中的文件
		List<String> list = new ArrayList<String>();

		// 声明一个FolderProcessor任务的数列，用来保存将要处理存储在文件夹内的子文件夹的子任务
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
		File file = new File(path);
		File[] content = file.listFiles();

		// 对于文件夹里的每个元素，如果是子文件夹，则创建一个新的FolderProcessor对象
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					// 使用fork()方法异步地执行它
					//这个方法提交给池的任务将被执行，如果池中有空闲的工作线程或池可以创建一个新的工作线程。这个方法会立即返回
					task.fork();
					tasks.add(task);
				} else {
					// /使用checkFile()方法比较这个文件的扩展名和你想要查找的扩展名，如果它们相等，在前面声明的字符串数列中存储这个文件的全路径
					if (checkFile(content[i].getName())) {
						list.add(content[i].getAbsolutePath());
					}
				}
			}
			// 如果FolderProcessor子任务的数列超过50个元素，写入一条信息到控制台表明这种情况
			if (tasks.size() > 50)
				System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());

			// 调用辅助方法addResultsFromTask()，将由这个任务发起的子任务返回的结果添加到文件数列中。传入参数：字符串数列和FolderProcessor子任务数列。
			addResultsFromTasks(list, tasks);
		}
		return list;
	}

	// 对于保存在tasks数列中的每个任务，调用join()方法，这将等待任务执行的完成，并且返回任务的结果。使用addAll()方法将这个结果添加到字符串数列
	private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			list.addAll(item.join());
		}
	}
	
	
	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

}
