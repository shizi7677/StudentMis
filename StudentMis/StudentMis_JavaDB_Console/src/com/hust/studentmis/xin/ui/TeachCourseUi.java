package com.hust.studentmis.xin.ui;

import java.util.List;

import com.hust.studentmis.xin.business.TeachCourseManager;
import com.hust.studentmis.xin.entity.TeachCourseBean;
import com.hust.xin.common.MyScanner;

public class TeachCourseUi {

	private TeachCourseManager  teachCourseManager = new TeachCourseManager();
	private TeachCourseBean teachCourse  = new  TeachCourseBean();
	private String teachteachCourseNumber = null;

	public void add(){
		System.out.printf("�����ڿα��:");
		teachCourse.setTeachCourseNumber (MyScanner.inputString());
		System.out.printf("�������ʦ���:");
		teachCourse.setTeacherNumber (MyScanner.inputString());
		System.out.printf("������γ̱��:");
		teachCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("������γ̿�ʼʱ��:");
		teachCourse.setBeginDate(MyScanner.inputDate());
		System.out.printf("������γ̽���ʱ��:");
		teachCourse.setEndDate (MyScanner.inputDate());

		/*
		 * ��������ʹ�� == 1����ֹ���������
		 */
		if(teachCourseManager.add(teachCourse) == true) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	public void search(){
		System.out.printf("�������ѯ�Ŀγ̱��:");
		teachteachCourseNumber = MyScanner.inputString();
		teachCourse = teachCourseManager.search(teachteachCourseNumber);
		if(teachCourse != null ) {
			System.out.println(teachCourse);
		} else {
			System.out.println("��ѯʧ�ܣ�");
		}
	}

	public void remove(){
		System.out.printf("������Ҫɾ���γ̱��:");
		String teachteachCourseNumber = MyScanner.inputString();
		if(teachCourseManager.remove(teachteachCourseNumber) == true){
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public void modify() {
		System.out.printf("������Ҫ�޸ĵ��ڿα��:");
		teachteachCourseNumber = MyScanner.inputString();

		System.out.printf("�����ڿα��:");
		teachCourse.setTeachCourseNumber (MyScanner.inputString());
		System.out.printf("�������ʦ���:");
		teachCourse.setTeacherNumber (MyScanner.inputString());
		System.out.printf("������γ̱��:");
		teachCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("������γ̿�ʼʱ��:");
		teachCourse.setBeginDate(MyScanner.inputDate());
		System.out.printf("������γ̽���ʱ��:");
		teachCourse.setEndDate (MyScanner.inputDate());

		if(teachCourseManager.modify(teachCourse,teachteachCourseNumber)  == true){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}	
	}

	public void display(){
		List<TeachCourseBean> teachCourses = teachCourseManager.display() ;

		for(TeachCourseBean temp: teachCourses) {
			System.out.println(temp);
		}

	}

}
