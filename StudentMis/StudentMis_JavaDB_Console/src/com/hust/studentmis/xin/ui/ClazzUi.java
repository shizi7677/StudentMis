package com.hust.studentmis.xin.ui;

import java.util.List;

import com.hust.studentmis.xin.business.ClazzManager;
import com.hust.studentmis.xin.entity.ClazzBean;
import com.hust.xin.common.MyScanner;

public class ClazzUi {

	private ClazzManager  clazzManager = new ClazzManager();
	private ClazzBean clazz  = new ClazzBean();
	private String clazzNumber = null;

	public void add(){
		System.out.printf("������༶���:");
		clazz.setClazzNumber(MyScanner.inputString());
		System.out.printf("������༶����:");
		clazz.setClazzName(MyScanner.inputString());
		System.out.printf("������༶��ʼʱ��:");
		clazz.setBeginDate(MyScanner.inputDate());
		System.out.printf("������༶����ʱ��:");
		clazz.setEndDate(MyScanner.inputDate());
		System.out.printf("��������ʦ���:");
		clazz.setTeacherNumber(MyScanner.inputString());


		
		if(clazzManager.add(clazz) == true) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	public void search(){
		System.out.printf("�������ѯ�İ༶���:");
		clazzNumber = MyScanner.inputString();
		clazz = clazzManager.search(clazzNumber);
		if(clazz != null ) {
			System.out.println(clazz);
		} else {
			System.out.println("��ѯʧ�ܣ�");
		}
	}

	public void remove(){
		System.out.printf("������Ҫɾ���༶���:");
		String clazzNumber = MyScanner.inputString();
		if(clazzManager.remove(clazzNumber) == true){
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public void modify() {
		System.out.printf("������Ҫ�޸ĵİ༶���:");
		clazzNumber = MyScanner.inputString();

		System.out.printf("������༶���:");
		clazz.setClazzNumber(MyScanner.inputString());
		System.out.printf("������༶����:");
		clazz.setClazzName(MyScanner.inputString());
		System.out.printf("������༶��ʼʱ��:");
		clazz.setBeginDate(MyScanner.inputDate());
		System.out.printf("������༶����ʱ��:");
		clazz.setEndDate(MyScanner.inputDate());
		System.out.printf("��������ʦ���:");
		clazz.setTeacherNumber(MyScanner.inputString());

		if(clazzManager.modify(clazz,clazzNumber)  == true){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}	
	}

	public void display(){
		List<ClazzBean> clazzs = clazzManager.display() ;

		for(ClazzBean temp: clazzs) {
			System.out.println(temp);
		}

	}

}
