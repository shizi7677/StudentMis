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
		return  super.toString() + "\t��ʦ�ܹ��ʣ�" +  this.getSalary()  ;
	}
	
	public void input(){
		MyScanner scanner =  new MyScanner();
		super.input();
		System.out.printf("�������ʱ��");
		hours =scanner.inputInt();
	
		System.out.printf("�������ʱ�ѣ�");
		cast = scanner.inputFloat();
	}

 

}
