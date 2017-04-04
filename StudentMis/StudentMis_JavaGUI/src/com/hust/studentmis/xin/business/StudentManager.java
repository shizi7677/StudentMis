package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.StudentDao;
import com.hust.studentmis.xin.entity.StudentBean;

public class StudentManager {

	private StudentDao studentDao = new StudentDao();

	public boolean add(StudentBean student){

		try {
			//��������
			student.setStudentNumber(student.getStudentNumber().trim());
			student.setStudentName(student.getStudentName().trim());
			return studentDao.insert(student) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDENTS") >= 0)
				throw new RuntimeException("ѧ������Ѵ��ڣ�������ӣ�");
			else if (e.getMessage().indexOf("UQ_STUDENTS_TELE") >= 0)
				throw new RuntimeException("�ֻ�����ʹ�ã�����������ֻ���������ӣ�");
			else if (e.getMessage().indexOf("UQ_STUDENTS_EMAIl") >= 0)
				throw new RuntimeException("������ʹ�ã��������������������ӣ�");
			else if (e.getMessage().indexOf("FK_CLAZZES_STUDENTS") >= 0)
				throw new RuntimeException("�༶�����ڣ�������ӣ�");
			else  
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());

		}
	}

	public boolean remove(String studentNumber){
		try {
			return studentDao.delete(studentNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_studentS") >= 0){
				throw new RuntimeException("ѧ����Ų����ڣ�");
			} 	else if (e.getMessage().indexOf("FK_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("��ѧ����Ϣ�������ã�����ɾ����");
			else {
				throw new RuntimeException("ɾ����������ԭ��" + e.getMessage());
			}
		}
	}

	public boolean modify(StudentBean student, String studentNumber) {
		try {
			//��������
			student.setStudentNumber(student.getStudentNumber().trim());
			student.setStudentName(student.getStudentName().trim());

			return studentDao.update(student, studentNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDENTS") >= 0)
				throw new RuntimeException("ѧ������Ѵ��ڣ������޸ģ�");
			else if (e.getMessage().indexOf("UQ_STUDENTS_TELE") >= 0)
				throw new RuntimeException("�ֻ�����ʹ�ã�����������ֻ���������ӣ�");
			else if (e.getMessage().indexOf("UQ_STUDENTS_EMAIl") >= 0)
				throw new RuntimeException("������ʹ�ã��������������������ӣ�");
			else  if (e.getMessage().indexOf("FK_CLAZZES_STUDENTS") >= 0)
				throw new RuntimeException("�༶�����ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FK_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("��ѧ����Ϣ�������ã������޸�����ֵ��");
			else {
				throw new RuntimeException("��ӳ�������ԭ��" + e.getMessage());
			}
		}

	}

	public StudentBean search(String studentNumber){
		try {
			return studentDao.selectOne(studentNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("���ҳ�������ԭ��" + e.getMessage());
		}
	}

	public List<StudentBean> display(){
		try {
			return studentDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("��ʾ��������ԭ��" + e.getMessage());
		}
	}

}
