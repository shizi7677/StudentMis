package com.hlo.xin;

public class Programmer extends Student implements Programmable {

	@Override
	public void debugging() {
		System.out.println("����Ա�����С���"); 
	}

	@Override
	public void programming() {
		System.out.println("����Ա����С���");
	}

}
