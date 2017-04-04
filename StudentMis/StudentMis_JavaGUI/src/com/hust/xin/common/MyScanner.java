package com.hust.xin.common;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 控制台输入输出工具类
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
			System.out.printf("输入格式错误！请输入浮点数，重新输入:");
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
			System.out.println("输入格式错误！请输入整数，重新输入:");
			return inputInt();
		}
	}


	public static String inputSex() {
		String sex = "";
		do {
		sex =  inputString();
		if( !(sex.equals("男")|| sex.equals("女"))){
			System.out.printf("请输入 男  or 女  ，重新输入：");
		}
		}while ( !(sex.equals("男")  ||  sex.equals("女"))  );
		 
		return sex;
	}
	
	public static int inputInt(int min, int max) {
		int i;
		do {
			i =  inputInt();
		if(i < min || i > max ){
			System.out.printf("请输入"  + min + "~" + max + "之内的数，重新输入：");
		}
		}while (i < min || i > max  );
		 
		return i;
	}
	
	public static Date inputDate(){
		Date date = null;
		try { 
			date = Date.valueOf(inputString());
		} catch(Exception e){
			System.out.printf("输入格式错误！请输入日期(yyyy-MM-dd) ，重新输入:");
			return inputDate();
		}
		return date;
	}

}
