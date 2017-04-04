package com.hust.xin.common;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * ����̨�������������
 * @author ttc
 *
 */
public class MyScanner {
	public static float inputFloat(){
		float f = 0;
		Scanner scan = new Scanner(System.in);
		try { 
			f = scan.nextFloat();
			return f;
		} catch(InputMismatchException e){
			System.out.printf("�����ʽ���������븡��������������:");
			return inputFloat();
		}
	}

	public static String inputString() {
		String s = "";
		Scanner scan = new Scanner(System.in);
		s = scan.next();
		return s;
	}


	public static int inputInt() {
		int i = 0;
		Scanner scan = new Scanner(System.in);
		try {
			i = scan.nextInt();
			return i;
		} catch(InputMismatchException e){
			System.out.println("�����ʽ������������������������:");
			return inputInt();
		}
	}


	public static String inputSex() {
		String sex = "";
		do {
		sex =  inputString();
		if( !(sex.equals("��")|| sex.equals("Ů"))){
			System.out.printf("������ ��  or Ů  ���������룺");
		}
		}while ( !(sex.equals("��")  ||  sex.equals("Ů"))  );
		 
		return sex;
	}
	
	public static int inputInt(int min, int max) {
		int i;
		do {
			i =  inputInt();
		if(i < min || i > max ){
			System.out.printf("������"  + min + "~" + max + "֮�ڵ������������룺");
		}
		}while (i < min || i > max  );
		 
		return i;
	}
	
	public static Date inputDate(){
		Date date = null;
		try { 
			date = Date.valueOf(inputString());
		} catch(Exception e){
			System.out.printf("�����ʽ��������������(yyyy-MM-dd) ����������:");
			return inputDate();
		}
		return date;
	}

}
