package com.hust.studentmis.xin.ui;

import com.hust.xin.common.MyScanner;

public class CourseMenu implements Menu{
	
	private CourseUi courseUi = new CourseUi();
	
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
				courseUi.add();
				break;
			case 2:
				courseUi.remove();
				break;
			case 3:
				courseUi.modify();
				break;
			case 4:
				courseUi.search();
				break;
			case 5:
				courseUi.display();
				break;

			default:
				System.out.println("���޸ù��ܣ�");
				break;
			}
		} while (choice!=0);

	}
	public static void printfMenu(){
		System.out.println("\n 1����� 2��ɾ��  3���޸�  4����ѯ  5����ʾ  0������");
	}


}
