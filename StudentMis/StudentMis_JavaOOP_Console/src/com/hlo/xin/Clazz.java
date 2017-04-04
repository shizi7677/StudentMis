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
			System.out.print("�Ƿ������y/n��:");
			cont  =  MyScanner.inputString(); 
		} while (cont.equals("y") );
	}
	public void output(){
		if(studentList.size() == 0) {
			System.out.println("ѧ����¼Ϊ�գ�");
		} else {
			for(Student s: studentList) {
				System.out.println(s ); 
			}
		}
	}
	public void add(){
		int index = 0;
		System.out.printf("��������ӵ�λ�ã�");
		index = MyScanner.inputInt(1, studentList.size() + 1);

		Student s = new Student();
		s.input();
		studentList.add(index - 1, s);
		System.out.println("��ӳɹ���");
	}
	public void modify(){
		String number = "";
		System.out.println("������Ҫ�޸�ѧ��ѧ�ţ�");
		number = MyScanner.inputString();

		Student s = findStudentByNumber(number);

		if(s == null) {
			System.out.println("���޴�ѧ���������޸ģ�");
		} else {
			System.out.println("��ѡ���޸�� 1:ѧ��  2������ 3���Ա� 4���ɼ�   ");

			int choice = MyScanner.inputInt();
			switch(choice) {
			case 1:
				System.out.println("ѧ���޸�Ϊ��");
				String num = MyScanner.inputString();
				s.setNumber(num);
				break;
			case 2:
				System.out.println("�����޸�Ϊ��");
				String name = MyScanner.inputString();
				s.setName(name);
				break;
			case 3:
				System.out.println("�Ա��޸�Ϊ��");
				String  sex = MyScanner.inputString();
				s.setSex(sex);
				break;
			case 4:
				System.out.println("�ɼ��޸�Ϊ��");
				int score = MyScanner.inputInt(1,100);
				s.setScore(score);
				break;
			default :
				System.out.println("��������");
				break;
			}

			System.out.println("�޸ĳɹ���");
			System.out.println("�޸ĺ�" + s);
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
		System.out.println("������Ҫɾ��ѧ��ѧ�ţ�");
		number = MyScanner.inputString();

		int flag = findByNumber(number);
		if(flag < 0) {
			System.out.println("���޴�ѧ��������ɾ����");
		} else {
			studentList.remove(index - 1);
			System.out.println("ɾ���ɹ���");
		}
	}
	public void search (){
		String number = "";
		System.out.println("������Ҫ����ѧ��ѧ�ţ�");
		number = MyScanner.inputString();

		int flag = findByNumber(number);
		if(flag < 0) {
			System.out.println("���޴�ѧ����");
		} else {
			System.out.println("���ҳɹ���");
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

				System.out.println("����ɹ�����");
			} catch (FileNotFoundException e) {
				System.out.println("����ѧ����Ϣʱ���ļ�δ�ҵ�����");
			} catch (IOException e) {
				System.out.println("����ѧ����Ϣʱ����");
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

				System.out.println("���سɹ�����������" + studentList.size() + "��ѧ����Ϣ��");
			} catch (FileNotFoundException e) {
				System.out.println("����ѧ����Ϣʱ���ļ�δ�ҵ�����");
			} catch (IOException e) {
				System.out.println("����ѧ����Ϣʱ����");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				MyIO.closeInputStream(fis);
				MyIO.closeInputStream(ois);
			}
		}


	}
