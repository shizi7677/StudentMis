package com.hust.studentmis.xin.ui;

import java.util.List;

import com.hust.studentmis.xin.business.ClazzManager;
import com.hust.studentmis.xin.entity.ClazzBean;
import com.hust.xin.common.MyScanner;

public class ClazzUi {

	private ClazzManager  clazzManager = new ClazzManager();
	private ClazzBean clazz  = new ClazzBean();
	private String clazzNumber = null;

	public void add(){
		System.out.printf("请输入班级编号:");
		clazz.setClazzNumber(MyScanner.inputString());
		System.out.printf("请输入班级名称:");
		clazz.setClazzName(MyScanner.inputString());
		System.out.printf("请输入班级起始时间:");
		clazz.setBeginDate(MyScanner.inputDate());
		System.out.printf("请输入班级结束时间:");
		clazz.setEndDate(MyScanner.inputDate());
		System.out.printf("请输入老师编号:");
		clazz.setTeacherNumber(MyScanner.inputString());


		
		if(clazzManager.add(clazz) == true) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	public void search(){
		System.out.printf("请输入查询的班级编号:");
		clazzNumber = MyScanner.inputString();
		clazz = clazzManager.search(clazzNumber);
		if(clazz != null ) {
			System.out.println(clazz);
		} else {
			System.out.println("查询失败！");
		}
	}

	public void remove(){
		System.out.printf("请输入要删除班级编号:");
		String clazzNumber = MyScanner.inputString();
		if(clazzManager.remove(clazzNumber) == true){
			System.out.println("删除成功！");
		}else {
			System.out.println("删除失败！");
		}
	}

	public void modify() {
		System.out.printf("请输入要修改的班级编号:");
		clazzNumber = MyScanner.inputString();

		System.out.printf("请输入班级编号:");
		clazz.setClazzNumber(MyScanner.inputString());
		System.out.printf("请输入班级名称:");
		clazz.setClazzName(MyScanner.inputString());
		System.out.printf("请输入班级起始时间:");
		clazz.setBeginDate(MyScanner.inputDate());
		System.out.printf("请输入班级结束时间:");
		clazz.setEndDate(MyScanner.inputDate());
		System.out.printf("请输入老师编号:");
		clazz.setTeacherNumber(MyScanner.inputString());

		if(clazzManager.modify(clazz,clazzNumber)  == true){
			System.out.println("修改成功！");
		}else {
			System.out.println("修改失败！");
		}	
	}

	public void display(){
		List<ClazzBean> clazzs = clazzManager.display() ;

		for(ClazzBean temp: clazzs) {
			System.out.println(temp);
		}

	}

}
