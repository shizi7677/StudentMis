package com.hlo.xin;

import java.io.Serializable;
import java.util.Scanner;

import com.hust.xin.common.MyScanner;
import com.hust.xin.exception.ScoreException;



// 继承：是由现有类创建新类的过程，这个现有类被称为父类、基类、超类
//      这个新类被称为子类、派生类。派生类继承了基类的全部特性。
public class Student extends Person implements Comparable<Student>  ,Serializable {
	private String number = "";
	private float score = 0;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;	// this在成员方法中区分同名局部变量和成员变量
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

	// 重写（override）是指在具有继承关系的不同区域内，同名、参数相同、返回值类型也相同的方法，
	// 派生类方法将覆盖基类方法，称之为派生类中重写了基类的方法。


	@Override
	public String toString() {
		return  "学号" + number + " 姓名：" + super.getName() + "  性别："+ super.getSex() + "  成绩："+  getScore();
	}

	public void input(){
		System.out.printf("请输入学号：");
		number = MyScanner.inputString();
		super.input();
		System.out.printf("请输入成绩：");
		setScore( MyScanner.inputInt(1,100));
	}
	@Override
	public int compareTo(Student o) {
		return this.getNumber().compareTo(o.getNumber());
	}
}
