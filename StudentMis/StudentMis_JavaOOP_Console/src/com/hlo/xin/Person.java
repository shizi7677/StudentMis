package com.hlo.xin;

import java.io.Serializable;
import java.util.Scanner;

import com.hust.xin.common.MyScanner;
import com.hust.xin.exception.SexException;


public class Person extends Object implements Serializable {
	private String name = "";
	private String sex = "";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
			this.sex = sex;
	}

	// ���췽������ͬ��
	// �в���������Ҫ����
	// �޷���ֵ�����Ƿ���void��
	// ����ֱ�ӵ��ã���Ҫͨ��new����
	// ���û�ж��幹�췽����ϵͳ���ṩĬ���޲ι��췽������������˹��췽������Ĭ���޲ι��췽����Ч
	// ���췽��ͨ���ǹ��еģ��������й��췽��Ҳ���������
	public Person() {
		// ��super�����ڹ��췽���ĵ�һ������У�super()��ʾֱ�ӻ���Ĺ��췽����
		super();
	}
	// ���أ�overload������ͬһ�����ڣ�ͬ����������ͬ�����������ͻ�˳��ͬ���ķ�����Ϊ���أ��Ǿ�̬�Ķ�̬��
	// ��������޹أ��뷵��ֵ�����޹ء�
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}

	public String toString() {
		return  "������" + name + "\t�Ա�"+ sex + "\t";
	}

	public void input(){
		System.out.printf("������������");
		setName(MyScanner.inputString());
		System.out.printf("�������Ա�(�� orŮ)��");
		setSex( MyScanner.inputSex());
	}

}
