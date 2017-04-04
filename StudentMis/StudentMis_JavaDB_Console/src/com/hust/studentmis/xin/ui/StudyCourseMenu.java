package com.hust.studentmis.xin.ui;


import com.hust.xin.common.MyScanner;

public class StudyCourseMenu implements Menu{
	
	private StudyCourseUi studyCourseUi = new StudyCourseUi();
	
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
				studyCourseUi.add();
				break;
			case 2:
				studyCourseUi.remove();
				break;
			case 3:
				studyCourseUi.modify();
				break;
			case 4:
				studyCourseUi.search();
				break;
			case 5:
				studyCourseUi.display();
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
