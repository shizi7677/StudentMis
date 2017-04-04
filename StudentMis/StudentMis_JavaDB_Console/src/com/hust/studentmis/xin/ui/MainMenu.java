package com.hust.studentmis.xin.ui;

import com.hust.xin.common.MyScanner;

public class MainMenu {
	Menu menu = new TeachCourseMenu();
	
	public	void run(){
		int choice = 0;
		do{
			printfMenu();
			System.out.println("请选择");
			choice = MyScanner.inputInt(0, 5);
			switch(choice){
			case 0:
				System.out.println("返回上一级菜单……");
				break;
			case 1:
				menu = new TeacherMenu();
				break;
			case 2:
				menu = new TeachCourseMenu();
				break;
			case 3:
				menu = new StudentMenu();
				break;
			case 4:
				menu = new TeachCourseMenu();
				break;
			case 5:
				menu = new ClazzMenu();
				break;

			default:
				System.out.println("暂无该功能！");
				break;
			}
			menu.run();
		} while (choice!=0);

	}
	public static void printfMenu(){
		System.out.println("\n 1：教师管理 2：授课管理  3：学生管理  4：选课管理  5：班级管理  0：返回");
	}

}
