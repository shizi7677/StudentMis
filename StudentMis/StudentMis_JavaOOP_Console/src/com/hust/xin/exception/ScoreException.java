package com.hust.xin.exception;



public class ScoreException extends RuntimeException {
	public ScoreException(){
		super("成绩只能是0-100");
	}
	

	public ScoreException(String message){
		super(message);
	}
}
