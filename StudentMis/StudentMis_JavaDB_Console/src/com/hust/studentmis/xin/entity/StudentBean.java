package com.hust.studentmis.xin.entity;

import java.io.Serializable;
import java.sql.Date;

public class StudentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String studentNumber = "";
	private String studentName = "";
	private String gender = "";
	private Date birthday = new Date(System.currentTimeMillis());;
	private String phoneNumber = "";
	private String address = "";
	private String clazzNumber = "";
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClazzNumber() {
		return clazzNumber;
	}
	public void setClazzNumber(String clazzNumber) {
		this.clazzNumber = clazzNumber;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t",
				this.studentNumber,this.studentName,this.gender,this.birthday,this.phoneNumber,this.address,this.clazzNumber);
	}
	
}
