package com.hlo.xin;

import java.io.Serializable;
import java.util.Scanner;

import com.hust.xin.common.MyScanner;
import com.hust.xin.exception.ScoreException;



// �̳У����������ഴ������Ĺ��̣���������౻��Ϊ���ࡢ���ࡢ����
//      ������౻��Ϊ���ࡢ�����ࡣ������̳��˻����ȫ�����ԡ�
public class Student extends Person implements Comparable<Student>  ,Serializable {
	private String number = "";
	private float score = 0;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;	// this�ڳ�Ա����������ͬ���ֲ������ͳ�Ա����
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}

	public Student() {
		super();
	}
	public Student(String number, String name, String sex) {
		super(name,sex);
		this.number = number;
	}

	// ��д��override����ָ�ھ��м̳й�ϵ�Ĳ�ͬ�����ڣ�ͬ����������ͬ������ֵ����Ҳ��ͬ�ķ�����
	// �����෽�������ǻ��෽������֮Ϊ����������д�˻���ķ�����


	@Override
	public String toString() {
		return  "ѧ��" + number + " ������" + super.getName() + "  �Ա�"+ super.getSex() + "  �ɼ���"+  getScore();
	}

	public void input(){
		System.out.printf("������ѧ�ţ�");
		number = MyScanner.inputString();
		super.input();
		System.out.printf("������ɼ���");
		setScore( MyScanner.inputInt(1,100));
	}
	@Override
	public int compareTo(Student o) {
		return this.getNumber().compareTo(o.getNumber());
	}
}
