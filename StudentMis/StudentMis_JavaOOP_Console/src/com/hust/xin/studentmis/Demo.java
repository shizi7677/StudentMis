package com.hust.xin.studentmis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.hlo.xin.Clazz;
import com.hlo.xin.Student;
import com.hust.xin.common.MyIO;
import com.hust.xin.common.MyScanner;

public class Demo {


	public static void main(String[] args)  {
		Clazz clazz = new Clazz();

		System.out.println("��ӭʹ�ñ�ϵͳ��");

		int choice = 0 ;
		do {
			printfMenu();
			System.out.println("��ѡ��");
			choice =  MyScanner.inputInt();
			switch(choice){
			case 0:
				break;
			case 1:
				clazz.input();
				break;
			case 2:
				clazz.output();
				break;
			case 3:
				clazz.add();
				break;
			case 4:
				clazz.remove();
				break;
			case 5:
				clazz.modify();
				break;
			case 6:
				clazz.search();
				break;
			case 7:
				printfSortMenu();
				System.out.print("��ѡ��:  ");
				int c = MyScanner.inputInt();
				switch(c) {
				case 1:
					 clazz.sortByNumber();
					 System.out.println("ѧ������ɹ�");
					break;
				case 2:
					 clazz.sortByName();
						System.out.println("��������ɹ�");
					break;
				case 3:
					 clazz.sortByScore();
						System.out.println("�ɼ�����ɹ�");
					break;
				}
				break;
			case 8:
				clazz.save();
				break;
			case 9:
				clazz.load();
				break;
			default:
				System.out.println("���޸ù��ܣ�");
				break;
			}
		} while(choice != 0);
		System.out.println("ллʹ�ã�");
	} 

	
	public static void printfSortMenu() {
		System.out.printf("1:��ѧ������  2������������  3�����ɼ����� ");
	}
	public static void printfMenu(){
		System.out.println(" 0:�˳�  1:����  2����� 3����� 4��ɾ��  5���޸�  6����ѯ  7������  8������  9������");
	}

}
