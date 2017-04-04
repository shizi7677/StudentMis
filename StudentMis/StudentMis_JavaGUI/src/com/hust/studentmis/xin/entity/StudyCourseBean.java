package com.hust.studentmis.xin.entity;

import java.io.Serializable;
import java.sql.Date;

public class StudyCourseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private StudentBean student;
	private CourseBean course;
	
	private String studyCourseNumber = "";
	private String studentNumber = "";
	private String courseNumber = "";
	private float score = 0;
	private Date examDate  = new Date(System.currentTimeMillis());
	public String getStudyCourseNumber() {
		return studyCourseNumber;
	}
	public void setStudyCourseNumber(String studyCourseNumber) {
		this.studyCourseNumber = studyCourseNumber;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
  
	public void setStudent(StudentBean student) {
		this.student = student;
	}
	public StudentBean getStudent() {
		return student;
	}
	public void setCourse(CourseBean course) {
		this.course = course;
	}
	public CourseBean getCourse() {
		return course;
	}
	
	public String toString() {
		return String.format("%s\t%s\t%s\t%.2f\t%s\t",this.studyCourseNumber,this.getStudent().getStudentName(),this.course.getCourseName(),this.score,this.examDate);
	}
}
