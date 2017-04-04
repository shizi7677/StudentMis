package com.hust.xin.exception;

public class SexException extends RuntimeException {
	

	public SexException(){
		super("性别只能是男or女");
	}
	

	public SexException(String message){
		super(message);
	}
}
