package com.lyj.multithread.sync.condition;

/**
 * һ�������ܰ����Ŷ����������Щ����������Condition�ӿ��С� ��Щ������Ŀ���������߳�ӵ�����Ŀ��Ʋ��Ҽ�������Ƿ�Ϊtrue��
 * �����false����ô�߳̽���������ֱ�������̻߳������ǡ�
 * 
 * Condition�ӿ��ṩһ�ֻ��ƣ�����һ���̺߳ͻ���һ�����������̡߳�
 * 
 * @author Ja0ck5
 *
 */
public class FileMock {
	/**
	 * �洢�ļ�����
	 */
	private String content[];
	/**
	 * ����������ģ���ļ�������
	 */
	private int index;

	public FileMock(int size, int length) {
		content = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder buffer = new StringBuilder(length);
			for (int j = 0; j < length; j++) {
				int indice = (int) Math.random() * 255;
				buffer.append((char) indice);
			}
			content[i] = buffer.toString();// ����ַ���ʼ���ļ�������
		}
		index = 0;
	}

	/**
	 * ����ļ��и�������������򷵻�true�� ��������Ѿ�ȡ����ģ���ļ���β�����򷵻�false
	 *
	 * @return
	 */
	public boolean hasMoreLines() {
		return index < content.length;
	}

	/**
	 * ����index������ȷ����������������ֵ
	 * @return
	 */
	public String getLine() {
		if (this.hasMoreLines()) {
			System.out.println("Mock: " + (content.length - index));
			return content[index++];
		}
		return null;
	}
	
	

}
