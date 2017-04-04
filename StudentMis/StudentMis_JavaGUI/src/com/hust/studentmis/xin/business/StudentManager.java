package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.StudentDao;
import com.hust.studentmis.xin.entity.StudentBean;

public class StudentManager {

	private StudentDao studentDao = new StudentDao();

	public boolean add(StudentBean student){

		try {
			//数据清理
			student.setStudentNumber(student.getStudentNumber().trim());
			student.setStudentName(student.getStudentName().trim());
			return studentDao.insert(student) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDENTS") >= 0)
				throw new RuntimeException("学生编号已存在，不能添加！");
			else if (e.getMessage().indexOf("UQ_STUDENTS_TELE") >= 0)
				throw new RuntimeException("手机号已使用，请更换其他手机号重新添加！");
			else if (e.getMessage().indexOf("UQ_STUDENTS_EMAIl") >= 0)
				throw new RuntimeException("邮箱已使用，请更换其他邮箱重新添加！");
			else if (e.getMessage().indexOf("FK_CLAZZES_STUDENTS") >= 0)
				throw new RuntimeException("班级不存在，不能添加！");
			else  
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());

		}
	}

	public boolean remove(String studentNumber){
		try {
			return studentDao.delete(studentNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_studentS") >= 0){
				throw new RuntimeException("学生编号不存在；");
			} 	else if (e.getMessage().indexOf("FK_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("该学生信息正被引用，不能删除！");
			else {
				throw new RuntimeException("删除出错，错误原因：" + e.getMessage());
			}
		}
	}

	public boolean modify(StudentBean student, String studentNumber) {
		try {
			//数据清理
			student.setStudentNumber(student.getStudentNumber().trim());
			student.setStudentName(student.getStudentName().trim());

			return studentDao.update(student, studentNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDENTS") >= 0)
				throw new RuntimeException("学生编号已存在，不能修改！");
			else if (e.getMessage().indexOf("UQ_STUDENTS_TELE") >= 0)
				throw new RuntimeException("手机号已使用，请更换其他手机号重新添加！");
			else if (e.getMessage().indexOf("UQ_STUDENTS_EMAIl") >= 0)
				throw new RuntimeException("邮箱已使用，请更换其他邮箱重新添加！");
			else  if (e.getMessage().indexOf("FK_CLAZZES_STUDENTS") >= 0)
				throw new RuntimeException("班级不存在，不能修改！");
			else if (e.getMessage().indexOf("FK_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("该学生信息正被引用，不能修改主键值！");
			else {
				throw new RuntimeException("添加出错，错误原因：" + e.getMessage());
			}
		}

	}

	public StudentBean search(String studentNumber){
		try {
			return studentDao.selectOne(studentNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("查找出错，错误原因：" + e.getMessage());
		}
	}

	public List<StudentBean> display(){
		try {
			return studentDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("显示出错，错误原因：" + e.getMessage());
		}
	}

}
