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
		System.out.printf("请输入教师编号:");
		teacher.setTeacherNumber(MyScanner.inputString());
		System.out.printf("请输入教师名称:");
		teacher.setTeacherName(MyScanner.inputString());
		System.out.printf("请输入教师性别:");
		teacher.setGender (MyScanner.inputSex());
		System.out.printf("请输入教师出生日期:");
		teacher.setBirthday(MyScanner.inputDate() );
		System.out.printf("请输入教师手机号:");
		teacher.setPhoneNumber(MyScanner.inputString());
		System.out.printf("请输入教师地址:");
		teacher.setAddress (MyScanner.inputString());
		 
		/*
		 * 条件不能使用 == 1，防止触发器作祟；
		 */
		if(teacherManager.add(teacher) == true) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	public void search(){
		System.out.printf("请输入查询的学生编号:");
		teacherNumber = MyScanner.inputString();
		teacher = teacherManager.search(teacherNumber);
		if(teacher != null ) {
			System.out.println(teacher);
		} else {
			System.out.println("查询失败！");
		}
	}

	public void remove(){
		System.out.println("请输入要删除学生编号:");
		String teacherNumber = MyScanner.inputString();
		if(teacherManager.remove(teacherNumber) == true){
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
	}

	public void modify() {
		System.out.printf("请输入要修改的学生编号:");
		teacherNumber = MyScanner.inputString();

		System.out.printf("请输入教师编号:");
		teacher.setTeacherNumber(MyScanner.inputString());
		System.out.printf("请输入教师名称:");
		teacher.setTeacherName(MyScanner.inputString());
		System.out.printf("请输入教师性别:");
		teacher.setGender (MyScanner.inputSex());
		System.out.printf("请输入教师出生日期:");
		teacher.setBirthday( MyScanner.inputDate() );
		System.out.printf("请输入教师手机号:");
		teacher.setPhoneNumber(MyScanner.inputString());
		System.out.printf("请输入教师地址:");
		teacher.setAddress (MyScanner.inputString());
		 
		if(teacherManager.modify(teacher,teacherNumber)  == true){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}	
	}

	public void display(){
		List<TeacherBean> teachers = teacherManager.display() ;

		for(TeacherBean temp: teachers) {
			System.out.println(temp);
		}

	}

}
