package com.hlo.xin;


import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
//		Person person = new Person();
//		Student student = new Student();
//		Employee teacher = new Teacher();
//		Employee manager = new Manager();

//		person.input();
//		student.input();
//		teacher.input();
//		manager.input();
		
//		System.out.println(person);
//		System.out.println(student);
//		System.out.println(manager.toString());

		
//		Programmable big = new ZhuBaJie();
//		Programmable programmer = new Programmer();
//		
//		big.programming();
//		programmer.programming();
		
		String s = "java";
		String s1 = "java";
		String s2 = new String("java");
		
		System.out.println(s == s1);
		System.out.println(s2 == s1);
		System.out.println(s.hashCode());
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	
	}

}
