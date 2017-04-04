package com.hlo.xin;

public class Programmer extends Student implements Programmable {

	@Override
	public void debugging() {
		System.out.println("程序员调试中……"); 
	}

	@Override
	public void programming() {
		System.out.println("程序员编程中……");
	}

}
