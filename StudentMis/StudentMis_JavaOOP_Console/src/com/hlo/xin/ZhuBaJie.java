package com.hlo.xin;

public class ZhuBaJie extends Student implements Programmable,Cookable{

	@Override
	public void debugging() {
		System.out.println("��˽�����С���"); 
	}

	@Override
	public void programming() {
		System.out.println("��˽����С���"); 
	}

	@Override
	public void cooking() {
		System.out.println("��˽�����С���"); 
	}

	@Override
	public void eatting() {
		System.out.println("��˽�Ʒ���С���"); 
	}

}
