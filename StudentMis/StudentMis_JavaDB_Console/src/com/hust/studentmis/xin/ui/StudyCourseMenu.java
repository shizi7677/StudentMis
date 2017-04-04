package com.hust.studentmis.xin.ui;


import com.hust.xin.common.MyScanner;

public class StudyCourseMenu implements Menu{
	
	private StudyCourseUi studyCourseUi = new StudyCourseUi();
	
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
				System.out.println("暂无该功能！");
				break;
			}
		} while (choice!=0);

	}
	public static void printfMenu(){
		System.out.println("\n 1：添加 2：删除  3：修改  4：查询  5：显示  0：返回");
	}


}
