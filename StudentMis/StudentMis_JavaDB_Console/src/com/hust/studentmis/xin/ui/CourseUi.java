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
		System.out.printf("请输入课程编号:");
		course.setCourseNumber(MyScanner.inputString());
		System.out.printf("请输入课程名称:");
		course.setCourseName(MyScanner.inputString());
		System.out.printf("请输入课程学分:");
		course.setCredit(MyScanner.inputFloat());
		System.out.printf("请输入课程学时:");
		course.setHours(MyScanner.inputFloat());
		System.out.printf("请输入课程描述:");
		course.setDescription (MyScanner.inputString());

		/*
		 * 条件不能使用 == 1，防止触发器作祟；
		 */
		if(courseManager.add(course) == true) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	public void search(){
		System.out.printf("请输入查询的课程编号:");
		courseNumber = MyScanner.inputString();
		course = courseManager.search(courseNumber);
		if(course != null ) {
			System.out.println(course);
		} else {
			System.out.println("查询失败！");
		}
	}

	public void remove(){
		System.out.printf("请输入要删除课程编号:");
		String courseNumber = MyScanner.inputString();
		if(courseManager.remove(courseNumber) == true){
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
	}

	public void modify() {
		System.out.printf("请输入要修改的课程编号:");
		courseNumber = MyScanner.inputString();

		System.out.printf("请输入课程编号:");
		course.setCourseNumber(MyScanner.inputString());
		System.out.printf("请输入课程名称:");
		course.setCourseName(MyScanner.inputString());
		System.out.printf("请输入课程学分:");
		course.setCredit(MyScanner.inputFloat());
		System.out.printf("请输入课程学时:");
		course.setHours(MyScanner.inputFloat());
		System.out.printf("请输入课程描述:");
		course.setDescription (MyScanner.inputString());

		if(courseManager.modify(course,courseNumber)  == true){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}	
	}

	public void display(){
		List<CourseBean> courses = courseManager.display() ;

		for(CourseBean temp: courses) {
			System.out.println(temp);
		}

	}

}
