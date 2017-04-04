package com.hlo.xin;

import java.util.Scanner;

import com.hust.xin.common.MyScanner;

public class Manager extends Employee {


	private float profit = 0;
	private float profit_rate = 0;
	
	@Override
	public float getSalary() {
		return super.getBaseSalary() + profit *  profit_rate;
	}

	
	public String toString() {
		return  super.toString() + "\t管理者工资：" + this.getSalary() + "\t" ;
	}
	
	public void input(){
		MyScanner scanner = new MyScanner();
		super.input();
		System.out.printf("请输入利润：");
		profit = scanner.inputFloat();
		System.out.printf("请输入利润率：");
		profit_rate = scanner.inputFloat();
	 
	}
}
