package com.hlo.xin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.hust.xin.common.MyIO;
import com.hust.xin.common.MyScanner;

public class Clazz {

	private ArrayList<Student> studentList = new ArrayList<Student>();

	public void input(){
		String cont = "";
		do {
			Student newStudent = new Student();
			newStudent.input();
			studentList.add(newStudent);	
			System.out.print("是否继续（y/n）:");
			cont  =  MyScanner.inputString(); 
		} while (cont.equals("y") );
	}
	public void output(){
		if(studentList.size() == 0) {
			System.out.println("学生记录为空！");
		} else {
			for(Student s: studentList) {
				System.out.println(s ); 
			}
		}
	}
	public void add(){
		int index = 0;
		System.out.printf("请输入添加的位置：");
		index = MyScanner.inputInt(1, studentList.size() + 1);

		Student s = new Student();
		s.input();
		studentList.add(index - 1, s);
		System.out.println("添加成功！");
	}
	public void modify(){
		String number = "";
		System.out.println("请输入要修改学生学号：");
		number = MyScanner.inputString();

		Student s = findStudentByNumber(number);

		if(s == null) {
			System.out.println("查无此学生，不能修改！");
		} else {
			System.out.println("请选择修改项： 1:学号  2：姓名 3：性别 4：成绩   ");

			int choice = MyScanner.inputInt();
			switch(choice) {
			case 1:
				System.out.println("学号修改为：");
				String num = MyScanner.inputString();
				s.setNumber(num);
				break;
			case 2:
				System.out.println("名字修改为：");
				String name = MyScanner.inputString();
				s.setName(name);
				break;
			case 3:
				System.out.println("性别修改为：");
				String  sex = MyScanner.inputString();
				s.setSex(sex);
				break;
			case 4:
				System.out.println("成绩修改为：");
				int score = MyScanner.inputInt(1,100);
				s.setScore(score);
				break;
			default :
				System.out.println("输入有误");
				break;
			}

			System.out.println("修改成功！");
			System.out.println("修改后：" + s);
		}
	}
	public Student findStudentByNumber(String number){
		for(Student s : studentList ){
			if(s.getNumber().equals(number)){
				System.out.println(s); 
				return s;
			}
		}
		return  null;
	}

	public void remove(){
		int index = 0;
		String number = "";
		System.out.println("请输入要删除学生学号：");
		number = MyScanner.inputString();

		int flag = findByNumber(number);
		if(flag < 0) {
			System.out.println("查无此学生，不能删除！");
		} else {
			studentList.remove(index - 1);
			System.out.println("删除成功！");
		}
	}
	public void search (){
		String number = "";
		System.out.println("请输入要查找学生学号：");
		number = MyScanner.inputString();

		int flag = findByNumber(number);
		if(flag < 0) {
			System.out.println("查无此学生！");
		} else {
			System.out.println("查找成功！");
		}
	}

	public int findByNumber(String number){
		for(Student s : studentList ){
			if(s.getNumber().equals(number)){
				System.out.println(s); 
				return 1;
			}
		}
		return -1;
	}




	public void sortByNumber(){
		Collections.sort(studentList);
	}
	public void sortByName(){
		Collections.sort(studentList, new Comparator<Person>(){
			@Override
			public int compare(Person o1, Person o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
	}
	public void sortByScore(){
		Collections.sort(studentList, new Comparator<Student>(){
			@Override
			public int compare(Student o1, Student o2) {
				return (int)(o1.getScore()>o2.getScore()? 1:o1.getScore()<o2.getScore()? -1 : 0);
			}
		});
		
		Collections.reverse(studentList);

	}


		public void save(){
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;

			try {
				fos =new FileOutputStream("E:/studentMIS.mis");
				oos = new ObjectOutputStream(fos);

				oos.writeObject(studentList);

				System.out.println("保存成功！！");
			} catch (FileNotFoundException e) {
				System.out.println("保存学生信息时，文件未找到！！");
			} catch (IOException e) {
				System.out.println("保存学生信息时出错");
			} finally {
				MyIO.closeOutputStream(fos);
				MyIO.closeOutputStream(oos);
			}
		}
		public void load(){
			FileInputStream fis = null;
			ObjectInputStream ois = null;

			try {
				fis =new FileInputStream("E:/studentMIS.mis");
				ois = new ObjectInputStream(fis);

				ArrayList<Student> students = (ArrayList<Student>)ois.readObject();

				System.out.println("加载成功！！加载了" + studentList.size() + "条学生信息。");
			} catch (FileNotFoundException e) {
				System.out.println("加载学生信息时，文件未找到！！");
			} catch (IOException e) {
				System.out.println("加载学生信息时出错");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				MyIO.closeInputStream(fis);
				MyIO.closeInputStream(ois);
			}
		}


	}
