package com.hlo.xin;

import java.util.Scanner;

import com.hust.xin.common.MyScanner;

public class Teacher extends Employee {

	private float hours = 0;
	private float cast = 0;
	
	
	
	@Override
	public float getSalary() {
		return super.getBaseSalary() + hours * cast;
	}
	
	
	@Override
	public String toString() {
		return  super.toString() + "\t老师总工资：" +  this.getSalary()  ;
	}
	
	public void input(){
		MyScanner scanner =  new MyScanner();
		super.input();
		System.out.printf("请输入课时：");
		hours =scanner.inputInt();
	
		System.out.printf("请输入课时费：");
		cast = scanner.inputFloat();
	}

 

}
