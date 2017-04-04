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

	// 构造方法与类同名
	// 有参数经常需要重载
	// 无返回值（不是返回void）
	// 不能直接调用，需要通过new调用
	// 如果没有定义构造方法，系统会提供默认无参构造方法，如果定义了构造方法，则默认无参构造方法无效
	// 构造方法通常是共有的，但是所有构造方法也是有意义的
	public Person() {
		// 当super出现在构造方法的第一个语句中，super()表示直接基类的构造方法。
		super();
	}
	// 重载（overload）是在同一区域内，同名但参数不同（个数、类型或顺序不同）的方法互为重载，是静态的多态。
	// 与参数名无关，与返回值类型无关。
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
		return  "姓名：" + name + "\t性别："+ sex + "\t";
	}

	public void input(){
		System.out.printf("请输入姓名：");
		setName(MyScanner.inputString());
		System.out.printf("请输入性别(男 or女)：");
		setSex( MyScanner.inputSex());
	}

}
