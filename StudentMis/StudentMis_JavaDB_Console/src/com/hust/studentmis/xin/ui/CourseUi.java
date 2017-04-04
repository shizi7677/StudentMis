package com.hust.studentmis.xin.ui;

import java.util.List;

import com.hust.studentmis.xin.business.CourseManager;
import com.hust.studentmis.xin.entity.CourseBean;
import com.hust.xin.common.MyScanner;

public class CourseUi {

	private CourseManager  courseManager = new CourseManager();
	private CourseBean course  = new CourseBean();
	private String courseNumber = null;

	public void add(){
		System.out.printf("������γ̱��:");
		course.setCourseNumber(MyScanner.inputString());
		System.out.printf("������γ�����:");
		course.setCourseName(MyScanner.inputString());
		System.out.printf("������γ�ѧ��:");
		course.setCredit(MyScanner.inputFloat());
		System.out.printf("������γ�ѧʱ:");
		course.setHours(MyScanner.inputFloat());
		System.out.printf("������γ�����:");
		course.setDescription (MyScanner.inputString());

		/*
		 * ��������ʹ�� == 1����ֹ���������
		 */
		if(courseManager.add(course) == true) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	public void search(){
		System.out.printf("�������ѯ�Ŀγ̱��:");
		courseNumber = MyScanner.inputString();
		course = courseManager.search(courseNumber);
		if(course != null ) {
			System.out.println(course);
		} else {
			System.out.println("��ѯʧ�ܣ�");
		}
	}

	public void remove(){
		System.out.printf("������Ҫɾ���γ̱��:");
		String courseNumber = MyScanner.inputString();
		if(courseManager.remove(courseNumber) == true){
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public void modify() {
		System.out.printf("������Ҫ�޸ĵĿγ̱��:");
		courseNumber = MyScanner.inputString();

		System.out.printf("������γ̱��:");
		course.setCourseNumber(MyScanner.inputString());
		System.out.printf("������γ�����:");
		course.setCourseName(MyScanner.inputString());
		System.out.printf("������γ�ѧ��:");
		course.setCredit(MyScanner.inputFloat());
		System.out.printf("������γ�ѧʱ:");
		course.setHours(MyScanner.inputFloat());
		System.out.printf("������γ�����:");
		course.setDescription (MyScanner.inputString());

		if(courseManager.modify(course,courseNumber)  == true){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}	
	}

	public void display(){
		List<CourseBean> courses = courseManager.display() ;

		for(CourseBean temp: courses) {
			System.out.println(temp);
		}

	}

}
