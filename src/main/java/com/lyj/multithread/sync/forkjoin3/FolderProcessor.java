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
		// ��������洢���ļ����е��ļ�
		List<String> list = new ArrayList<String>();

		// ����һ��FolderProcessor��������У��������潫Ҫ����洢���ļ����ڵ����ļ��е�������
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
		File file = new File(path);
		File[] content = file.listFiles();

		// �����ļ������ÿ��Ԫ�أ���������ļ��У��򴴽�һ���µ�FolderProcessor����
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					// ʹ��fork()�����첽��ִ����
					//��������ύ���ص����񽫱�ִ�У���������п��еĹ����̻߳�ؿ��Դ���һ���µĹ����̡߳������������������
					task.fork();
					tasks.add(task);
				} else {
					// /ʹ��checkFile()�����Ƚ�����ļ�����չ��������Ҫ���ҵ���չ�������������ȣ���ǰ���������ַ��������д洢����ļ���ȫ·��
					if (checkFile(content[i].getName())) {
						list.add(content[i].getAbsolutePath());
					}
				}
			}
			// ���FolderProcessor����������г���50��Ԫ�أ�д��һ����Ϣ������̨�����������
			if (tasks.size() > 50)
				System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(), tasks.size());

			// ���ø�������addResultsFromTask()���������������������񷵻صĽ����ӵ��ļ������С�����������ַ������к�FolderProcessor���������С�
			addResultsFromTasks(list, tasks);
		}
		return list;
	}

	// ���ڱ�����tasks�����е�ÿ�����񣬵���join()�������⽫�ȴ�����ִ�е���ɣ����ҷ�������Ľ����ʹ��addAll()��������������ӵ��ַ�������
	private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			list.addAll(item.join());
		}
	}
	
	
	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

}
