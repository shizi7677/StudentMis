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
		return  super.toString() + "\t�����߹��ʣ�" + this.getSalary() + "\t" ;
	}
	
	public void input(){
		MyScanner scanner = new MyScanner();
		super.input();
		System.out.printf("����������");
		profit = scanner.inputFloat();
		System.out.printf("�����������ʣ�");
		profit_rate = scanner.inputFloat();
	 
	}
}
