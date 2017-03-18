package com.lyj.multithread.sync.forkjoin2;

import java.util.Random;

public class Document {
	// ����һ������һЩ���ʵ��ַ������顣������齫�����������ַ�����ά���顣
	private String words[] = { "the", "hello", "goodbye", "packt", "java", "thread", "pool", "random", "class",
			"main" };

	// ʵ��generateDocument()���������������²�����������ÿ�еĵ�������������ӷ���һ���ַ�����ά���飬����ʾ��Ҫ���ҵĵ��ʡ�
	public String[][] generateDocument(int numLines, int numWords, String word) {

		// �ַ�����ά����������������Random����
		int counter = 0;
		String document[][] = new String[numLines][numWords];
		Random random = new Random();

		// ���ַ�������������
		for (int i = 0; i < numLines; i++) {
			for (int j = 0; j < numWords; j++) {
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if (document[i][j].equals(word)) {
					counter++;
				}
			}
		}
		// ���ʳ��ֵĴ���д�����̨�����������ɵĶ�ά����
		System.out.println("DocumentMock: The word appears "+counter+" times in the document");
		return document;
	}

}
