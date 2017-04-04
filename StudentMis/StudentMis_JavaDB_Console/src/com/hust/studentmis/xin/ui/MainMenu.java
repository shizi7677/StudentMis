package com.hust.studentmis.xin.ui;

import com.hust.xin.common.MyScanner;

public class MainMenu {
	Menu menu = new TeachCourseMenu();
	
	public	void run(){
		int choice = 0;
		do{
			printfMenu();
			System.out.println("��ѡ��");
			choice = MyScanner.inputInt(0, 5);
			switch(choice){
			case 0:
				System.out.println("������һ���˵�����");
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
				System.out.println("���޸ù��ܣ�");
				break;
			}
			menu.run();
		} while (choice!=0);

	}
	public static void printfMenu(){
		System.out.println("\n 1����ʦ���� 2���ڿι���  3��ѧ������  4��ѡ�ι���  5���༶����  0������");
	}

}
