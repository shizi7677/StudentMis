package com.hust.studentmis.xin.ui;

import java.util.List;

import com.hust.studentmis.xin.business.StudentManager;
import com.hust.studentmis.xin.entity.StudentBean;
import com.hust.xin.common.MyScanner;

public class StudentUi {

	private StudentManager  studentManager = new StudentManager();
	private StudentBean student  = new StudentBean();
	private String studentNumber = null;

	public void add(){
		System.out.printf("������ѧ�����:");
		student.setStudentNumber(MyScanner.inputString());
		System.out.printf("������ѧ������:");
		student.setStudentName(MyScanner.inputString());
		System.out.printf("������ѧ���Ա�:");
		student.setGender (MyScanner.inputSex());
		System.out.printf("������ѧ����������:");
		student.setBirthday(MyScanner.inputDate() );
		System.out.printf("������ѧ���ֻ���:");
		student.setPhoneNumber(MyScanner.inputString());
		System.out.printf("������ѧ����ַ:");
		student.setAddress (MyScanner.inputString());
		System.out.printf("������ѧ���༶��:");
		student.setClazzNumber(MyScanner.inputString());

		/*
		 * ��������ʹ�� == 1����ֹ���������
		 */
		if(studentManager.add(student) == true) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	public void search(){
		System.out.printf("�������ѯ��ѧ�����:");
		studentNumber = MyScanner.inputString();
		student = studentManager.search(studentNumber);
		if(student != null ) {
			System.out.println(student);
		} else {
			System.out.println("��ѯʧ�ܣ�");
		}
	}

	public void remove(){
		System.out.println("������Ҫɾ��ѧ�����:");
		String studentNumber = MyScanner.inputString();
		if(studentManager.remove(studentNumber) == true){
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public void modify() {
		System.out.printf("������Ҫ�޸ĵ�ѧ�����:");
		studentNumber = MyScanner.inputString();

		System.out.printf("������ѧ�����:");
		student.setStudentNumber(MyScanner.inputString());
		System.out.printf("������ѧ������:");
		student.setStudentName(MyScanner.inputString());
		System.out.printf("������ѧ���Ա�:");
		student.setGender (MyScanner.inputSex());
		System.out.printf("������ѧ����������:");
		student.setBirthday( MyScanner.inputDate() );
		System.out.printf("������ѧ���ֻ���:");
		student.setPhoneNumber(MyScanner.inputString());
		System.out.printf("������ѧ����ַ:");
		student.setAddress (MyScanner.inputString());
		System.out.printf("������ѧ���༶��:");
		student.setClazzNumber(MyScanner.inputString());

		if(studentManager.modify(student,studentNumber)  == true){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}	
	}

	public void display(){
		List<StudentBean> students = studentManager.display() ;

		for(StudentBean temp: students) {
			System.out.println(temp);
		}

	}

}
