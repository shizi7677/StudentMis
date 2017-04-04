package com.hust.studentmis.xin.ui;


import com.hust.xin.common.MyScanner;

public class TeacherMenu implements Menu{
	
	private TeacherUi teacherUi = new TeacherUi();
	
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
				teacherUi.add();
				break;
			case 2:
				teacherUi.remove();
				break;
			case 3:
				teacherUi.modify();
				break;
			case 4:
				teacherUi.search();
				break;
			case 5:
				teacherUi.display();
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
