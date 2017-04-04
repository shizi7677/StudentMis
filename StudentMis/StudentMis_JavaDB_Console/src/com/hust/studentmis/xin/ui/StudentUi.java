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
		System.out.printf("请输入学生编号:");
		student.setStudentNumber(MyScanner.inputString());
		System.out.printf("请输入学生名称:");
		student.setStudentName(MyScanner.inputString());
		System.out.printf("请输入学生性别:");
		student.setGender (MyScanner.inputSex());
		System.out.printf("请输入学生出生日期:");
		student.setBirthday(MyScanner.inputDate() );
		System.out.printf("请输入学生手机号:");
		student.setPhoneNumber(MyScanner.inputString());
		System.out.printf("请输入学生地址:");
		student.setAddress (MyScanner.inputString());
		System.out.printf("请输入学生班级号:");
		student.setClazzNumber(MyScanner.inputString());

		/*
		 * 条件不能使用 == 1，防止触发器作祟；
		 */
		if(studentManager.add(student) == true) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	public void search(){
		System.out.printf("请输入查询的学生编号:");
		studentNumber = MyScanner.inputString();
		student = studentManager.search(studentNumber);
		if(student != null ) {
			System.out.println(student);
		} else {
			System.out.println("查询失败！");
		}
	}

	public void remove(){
		System.out.println("请输入要删除学生编号:");
		String studentNumber = MyScanner.inputString();
		if(studentManager.remove(studentNumber) == true){
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
	}

	public void modify() {
		System.out.printf("请输入要修改的学生编号:");
		studentNumber = MyScanner.inputString();

		System.out.printf("请输入学生编号:");
		student.setStudentNumber(MyScanner.inputString());
		System.out.printf("请输入学生名称:");
		student.setStudentName(MyScanner.inputString());
		System.out.printf("请输入学生性别:");
		student.setGender (MyScanner.inputSex());
		System.out.printf("请输入学生出生日期:");
		student.setBirthday( MyScanner.inputDate() );
		System.out.printf("请输入学生手机号:");
		student.setPhoneNumber(MyScanner.inputString());
		System.out.printf("请输入学生地址:");
		student.setAddress (MyScanner.inputString());
		System.out.printf("请输入学生班级号:");
		student.setClazzNumber(MyScanner.inputString());

		if(studentManager.modify(student,studentNumber)  == true){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}	
	}

	public void display(){
		List<StudentBean> students = studentManager.display() ;

		for(StudentBean temp: students) {
			System.out.println(temp);
		}

	}

}
