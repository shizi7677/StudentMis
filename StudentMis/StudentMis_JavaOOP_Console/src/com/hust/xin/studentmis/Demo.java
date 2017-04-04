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

		System.out.println("欢迎使用本系统！");

		int choice = 0 ;
		do {
			printfMenu();
			System.out.println("请选择：");
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
				System.out.print("请选择:  ");
				int c = MyScanner.inputInt();
				switch(c) {
				case 1:
					 clazz.sortByNumber();
					 System.out.println("学号排序成功");
					break;
				case 2:
					 clazz.sortByName();
						System.out.println("姓名排序成功");
					break;
				case 3:
					 clazz.sortByScore();
						System.out.println("成绩排序成功");
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
				System.out.println("暂无该功能！");
				break;
			}
		} while(choice != 0);
		System.out.println("谢谢使用！");
	} 

	
	public static void printfSortMenu() {
		System.out.printf("1:按学号排序：  2：按姓名排序  3：按成绩排序 ");
	}
	public static void printfMenu(){
		System.out.println(" 0:退出  1:输入  2：输出 3：添加 4：删除  5：修改  6：查询  7：排序  8：保存  9：加载");
	}

}
