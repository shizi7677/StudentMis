package com.hust.xin.exception;



public class ScoreException extends RuntimeException {
	public ScoreException(){
		super("�ɼ�ֻ����0-100");
	}
	

	public ScoreException(String message){
		super(message);
	}
}
