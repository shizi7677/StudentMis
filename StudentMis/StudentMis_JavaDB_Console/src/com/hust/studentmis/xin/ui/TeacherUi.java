package com.hust.studentmis.xin.ui;
 
import java.util.List;

import com.hust.studentmis.xin.business.TeacherManager;
import com.hust.studentmis.xin.entity.TeacherBean;
import com.hust.xin.common.MyScanner;

public class TeacherUi {

	private TeacherManager  teacherManager = new TeacherManager();
	private TeacherBean teacher  = new TeacherBean();
	private String teacherNumber = null;

	public void add(){
		System.out.printf("�������ʦ���:");
		teacher.setTeacherNumber(MyScanner.inputString());
		System.out.printf("�������ʦ����:");
		teacher.setTeacherName(MyScanner.inputString());
		System.out.printf("�������ʦ�Ա�:");
		teacher.setGender (MyScanner.inputSex());
		System.out.printf("�������ʦ��������:");
		teacher.setBirthday(MyScanner.inputDate() );
		System.out.printf("�������ʦ�ֻ���:");
		teacher.setPhoneNumber(MyScanner.inputString());
		System.out.printf("�������ʦ��ַ:");
		teacher.setAddress (MyScanner.inputString());
		 
		/*
		 * ��������ʹ�� == 1����ֹ���������
		 */
		if(teacherManager.add(teacher) == true) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("���ʧ�ܣ�");
		}
	}

	public void search(){
		System.out.printf("�������ѯ��ѧ�����:");
		teacherNumber = MyScanner.inputString();
		teacher = teacherManager.search(teacherNumber);
		if(teacher != null ) {
			System.out.println(teacher);
		} else {
			System.out.println("��ѯʧ�ܣ�");
		}
	}

	public void remove(){
		System.out.println("������Ҫɾ��ѧ�����:");
		String teacherNumber = MyScanner.inputString();
		if(teacherManager.remove(teacherNumber) == true){
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("ɾ��ʧ�ܣ�");
		}
	}

	public void modify() {
		System.out.printf("������Ҫ�޸ĵ�ѧ�����:");
		teacherNumber = MyScanner.inputString();

		System.out.printf("�������ʦ���:");
		teacher.setTeacherNumber(MyScanner.inputString());
		System.out.printf("�������ʦ����:");
		teacher.setTeacherName(MyScanner.inputString());
		System.out.printf("�������ʦ�Ա�:");
		teacher.setGender (MyScanner.inputSex());
		System.out.printf("�������ʦ��������:");
		teacher.setBirthday( MyScanner.inputDate() );
		System.out.printf("�������ʦ�ֻ���:");
		teacher.setPhoneNumber(MyScanner.inputString());
		System.out.printf("�������ʦ��ַ:");
		teacher.setAddress (MyScanner.inputString());
		 
		if(teacherManager.modify(teacher,teacherNumber)  == true){
			System.out.println("�޸ĳɹ���");
		}else {
			System.out.println("�޸�ʧ�ܣ�");
		}	
	}

	public void display(){
		List<TeacherBean> teachers = teacherManager.display() ;

		for(TeacherBean temp: teachers) {
			System.out.println(temp);
		}

	}

}
