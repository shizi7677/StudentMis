package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.StudyCourseDao;
import com.hust.studentmis.xin.entity.StudyCourseBean;

public class StudyCourseManager {

	private StudyCourseDao studyCourseDao = new StudyCourseDao();

	public boolean add(StudyCourseBean studyCourse){

		try {
			//数据清理
			studyCourse.setStudyCourseNumber (studyCourse.getStudyCourseNumber().trim());
			studyCourse.setStudentNumber(studyCourse.getStudentNumber().trim());
			studyCourse.setCourseNumber(studyCourse.getCourseNumber().trim());

			return studyCourseDao.insert(studyCourse) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDYCOURSES") >= 0)
				throw new RuntimeException("选课编号已存在，不能修改！");
			else if (e.getMessage().indexOf("FQ_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("选课课程不存在，不能修改！");
			else if (e.getMessage().indexOf("FQ_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("选课学生不存在，不能修改！");
			else if (e.getMessage().indexOf("UQ_STU_COUR_NUMBER") >= 0)
				throw new RuntimeException("同一门课只能选一次，不能修改！");
			else
				throw new RuntimeException("修改出错，错误原因：" + e.getMessage());
		}
	}



	public boolean remove(String studyCourseNumber){
		try {
			return studyCourseDao.delete(studyCourseNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("选课编号不存在；");
			} else {
				throw new RuntimeException("删除出错，错误原因：" + e.getMessage());
			}
		}
	}

	public boolean modify(StudyCourseBean course, String studyCourseNumber) {
		try {
			//数据清理
			course.setStudyCourseNumber (course.getStudyCourseNumber().trim());
			course.setStudentNumber(course.getStudentNumber().trim());
			course.setCourseNumber(course.getCourseNumber().trim());

			return studyCourseDao.update(course, studyCourseNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDYCOURSES") >= 0)
				throw new RuntimeException("选课编号已存在，不能修改！");
			else if (e.getMessage().indexOf("FQ_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("选课课程不存在，不能修改！");
			else if (e.getMessage().indexOf("FQ_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("选课学生不存在，不能修改！");
			else if (e.getMessage().indexOf("UQ_STU_COUR_NUMBER") >= 0)
				throw new RuntimeException("同一门课只能选一次，不能修改！");
			else
				throw new RuntimeException("修改出错，错误原因：" + e.getMessage());
		}

	}

	public StudyCourseBean search(String studyCourseNumber){
		try {
			return studyCourseDao.selectOne(studyCourseNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("查找出错，错误原因：" + e.getMessage());
		}
	}

	public List<StudyCourseBean> display(){
		try {
			return studyCourseDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("显示出错，错误原因：" + e.getMessage());
		}
	}

}
