package com.hust.studentmis.xin.ui;

import java.util.List;

import com.hust.studentmis.xin.business.StudyCourseManager;
import com.hust.studentmis.xin.entity.StudyCourseBean;
import com.hust.xin.common.MyScanner;

public class StudyCourseUi {

	private StudyCourseManager  studyCourseManager = new StudyCourseManager();
	private StudyCourseBean studyCourse  = new  StudyCourseBean();
	private String studyCourseNumber = null;

	public void add(){
		System.out.printf("����ѡ�α��:");
		studyCourse.setStudyCourseNumber(MyScanner.inputString());
		System.out.printf("������ѧ�����:");
		studyCourse.setStudentNumber (MyScanner.inputString());
		System.out.printf("������γ̱��:");
		studyCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("������ɼ�:");
		studyCourse.setScore(MyScanner.inputFloat());
		System.out.printf("������γ̿���ʱ��:");
		studyCourse.setExamDate(MyScanner.inputDate());

		/*
		 * ��������ʹ�� == 1����ֹ���������
		 */
		if(studyCourseManager.add(studyCourse) == true) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	public void search(){
		System.out.printf("�������ѯ�Ŀγ̱��:");
		studyCourseNumber = MyScanner.inputString();
		studyCourse = studyCourseManager.search(studyCourseNumber);
		if(studyCourse != null ) {
			System.out.println(studyCourse);
		} else {
			System.out.println("��ѯʧ�ܣ�");
		}
	}

	public void remove(){
		System.out.printf("������Ҫɾ���γ̱��:");
		String studyCourseNumber = MyScanner.inputString();
		if(studyCourseManager.remove(studyCourseNumber) == true){
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public void modify() {
		System.out.printf("������Ҫ�޸ĵ�ѡ�α��:");
		studyCourseNumber = MyScanner.inputString();

		System.out.printf("����ѡ�α��:");
		studyCourse.setStudyCourseNumber(MyScanner.inputString());
		System.out.printf("������ѧ�����:");
		studyCourse.setStudentNumber (MyScanner.inputString());
		System.out.printf("������γ̱��:");
		studyCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("������ɼ�:");
		studyCourse.setScore(MyScanner.inputFloat());
		System.out.printf("������γ̿���ʱ��:");
		studyCourse.setExamDate(MyScanner.inputDate());

		if(studyCourseManager.modify(studyCourse,studyCourseNumber)  == true){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}	
	}

	public void display(){
		List<StudyCourseBean> studyCourses = studyCourseManager.display() ;

		for(StudyCourseBean temp: studyCourses) {
			System.out.println(temp);
		}

	}

}
