package com.hlo.xin;

import java.util.Scanner;

import com.hust.xin.common.MyScanner;

public abstract class Employee extends Person {

	private String number = "";
	private float baseSalary = 0;
	
	
	public Employee() {
		super();
	}

	public Employee(String number, float baseSalary) {
		super();
		this.number = number;
		this.baseSalary = baseSalary;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(float baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	@Override
	public String toString() {
		return "Ա���ţ�" + number + "\t" + super.toString() + "�������ʣ�" + baseSalary + "\t" ;
	}
	
	public void input(){
		MyScanner scanner =  new MyScanner() ;
		System.out.printf("������Ա���ţ�");
		number = scanner.inputString();
		super.input();
		System.out.printf("������������ʣ�");
		this.baseSalary = scanner.inputFloat();
		
		}
	
	public abstract float getSalary();
	
	
}
