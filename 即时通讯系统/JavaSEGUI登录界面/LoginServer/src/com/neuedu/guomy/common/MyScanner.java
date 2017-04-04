package com.neuedu.guomy.common;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyScanner {
	public String inputString() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	public String inputSex() {
		String sex = "";
		do {
			sex = inputString();
			if (sex.compareTo("��") != 0 && sex.compareTo("Ů") != 0)
				System.out.print("�����롰�С���Ů����");
		} while (sex.compareTo("��") != 0 
				&& sex.compareTo("Ů") != 0);
		return sex;
	}

	public float inputFloat() {
		float f = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			f = scanner.nextFloat();
			return f;
		} catch (InputMismatchException ex) {
			System.out.print("�����ʽ���������븡������");
			return inputFloat();
		}
	}
	public float inputFloat(float min, float max) {
		float f = 0;
		do {
			f = inputFloat();
			if (f < min || f > max)
				System.out.printf("������%d-%d֮���������", min, max);
		} while (f < min || f > max);
		return f;
	}
	
	public int inputInteger() {
		int i = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			i = scanner.nextInt();
			return i;
		} catch (InputMismatchException ex) {
			System.out.print("�����ʽ����������������");
			return inputInteger();
		}
	}
	public int inputInteger(int min, int max) {
		int i = 0;
		do {
			i = inputInteger();
			if (i < min || i > max)
				System.out.printf("������%d-%d֮���������", min, max);
		} while (i < min || i > max);
		return i;
	}
	
	public char inputChar() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine().charAt(0);
	}
	
	public Date inputDate() {
		Date date = null;
		Scanner scanner = new Scanner(System.in);
		try {
			date = Date.valueOf(scanner.nextLine());
			return date;
		} catch (IllegalArgumentException ex) {
			System.out.print("�����ʽ�������������ڣ�YYYY-MM-DD����");
			return inputDate();
		}
	}
}









