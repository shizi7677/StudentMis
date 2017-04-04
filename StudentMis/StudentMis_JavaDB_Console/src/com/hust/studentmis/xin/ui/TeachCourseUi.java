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
		System.out.printf("请输授课编号:");
		teachCourse.setTeachCourseNumber (MyScanner.inputString());
		System.out.printf("请输入教师编号:");
		teachCourse.setTeacherNumber (MyScanner.inputString());
		System.out.printf("请输入课程编号:");
		teachCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("请输入课程开始时间:");
		teachCourse.setBeginDate(MyScanner.inputDate());
		System.out.printf("请输入课程结束时间:");
		teachCourse.setEndDate (MyScanner.inputDate());

		/*
		 * 条件不能使用 == 1，防止触发器作祟；
		 */
		if(teachCourseManager.add(teachCourse) == true) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	public void search(){
		System.out.printf("请输入查询的课程编号:");
		teachteachCourseNumber = MyScanner.inputString();
		teachCourse = teachCourseManager.search(teachteachCourseNumber);
		if(teachCourse != null ) {
			System.out.println(teachCourse);
		} else {
			System.out.println("查询失败！");
		}
	}

	public void remove(){
		System.out.printf("请输入要删除课程编号:");
		String teachteachCourseNumber = MyScanner.inputString();
		if(teachCourseManager.remove(teachteachCourseNumber) == true){
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
	}

	public void modify() {
		System.out.printf("请输入要修改的授课编号:");
		teachteachCourseNumber = MyScanner.inputString();

		System.out.printf("请输授课编号:");
		teachCourse.setTeachCourseNumber (MyScanner.inputString());
		System.out.printf("请输入教师编号:");
		teachCourse.setTeacherNumber (MyScanner.inputString());
		System.out.printf("请输入课程编号:");
		teachCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("请输入课程开始时间:");
		teachCourse.setBeginDate(MyScanner.inputDate());
		System.out.printf("请输入课程结束时间:");
		teachCourse.setEndDate (MyScanner.inputDate());

		if(teachCourseManager.modify(teachCourse,teachteachCourseNumber)  == true){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}	
	}

	public void display(){
		List<TeachCourseBean> teachCourses = teachCourseManager.display() ;

		for(TeachCourseBean temp: teachCourses) {
			System.out.println(temp);
		}

	}

}
