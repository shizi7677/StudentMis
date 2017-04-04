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
		System.out.printf("请输选课编号:");
		studyCourse.setStudyCourseNumber(MyScanner.inputString());
		System.out.printf("请输入学生编号:");
		studyCourse.setStudentNumber (MyScanner.inputString());
		System.out.printf("请输入课程编号:");
		studyCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("请输入成绩:");
		studyCourse.setScore(MyScanner.inputFloat());
		System.out.printf("请输入课程考试时间:");
		studyCourse.setExamDate(MyScanner.inputDate());

		/*
		 * 条件不能使用 == 1，防止触发器作祟；
		 */
		if(studyCourseManager.add(studyCourse) == true) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	public void search(){
		System.out.printf("请输入查询的课程编号:");
		studyCourseNumber = MyScanner.inputString();
		studyCourse = studyCourseManager.search(studyCourseNumber);
		if(studyCourse != null ) {
			System.out.println(studyCourse);
		} else {
			System.out.println("查询失败！");
		}
	}

	public void remove(){
		System.out.printf("请输入要删除课程编号:");
		String studyCourseNumber = MyScanner.inputString();
		if(studyCourseManager.remove(studyCourseNumber) == true){
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
	}

	public void modify() {
		System.out.printf("请输入要修改的选课编号:");
		studyCourseNumber = MyScanner.inputString();

		System.out.printf("请输选课编号:");
		studyCourse.setStudyCourseNumber(MyScanner.inputString());
		System.out.printf("请输入学生编号:");
		studyCourse.setStudentNumber (MyScanner.inputString());
		System.out.printf("请输入课程编号:");
		studyCourse.setCourseNumber(MyScanner.inputString());
		System.out.printf("请输入成绩:");
		studyCourse.setScore(MyScanner.inputFloat());
		System.out.printf("请输入课程考试时间:");
		studyCourse.setExamDate(MyScanner.inputDate());

		if(studyCourseManager.modify(studyCourse,studyCourseNumber)  == true){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}	
	}

	public void display(){
		List<StudyCourseBean> studyCourses = studyCourseManager.display() ;

		for(StudyCourseBean temp: studyCourses) {
			System.out.println(temp);
		}

	}

}
