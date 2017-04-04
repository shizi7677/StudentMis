package com.hust.studentmis.xin.business;

import java.sql.SQLException;
import java.util.List;

import com.hust.studentmis.xin.dao.StudyCourseDao;
import com.hust.studentmis.xin.entity.StudyCourseBean;

public class StudyCourseManager {

	private StudyCourseDao studyCourseDao = new StudyCourseDao();

	public boolean add(StudyCourseBean studyCourse){

		try {
			//��������
			studyCourse.setStudyCourseNumber (studyCourse.getStudyCourseNumber().trim());
			studyCourse.setStudentNumber(studyCourse.getStudentNumber().trim());
			studyCourse.setCourseNumber(studyCourse.getCourseNumber().trim());

			return studyCourseDao.insert(studyCourse) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDYCOURSES") >= 0)
				throw new RuntimeException("ѡ�α���Ѵ��ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FQ_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("ѡ�ογ̲����ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FQ_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("ѡ��ѧ�������ڣ������޸ģ�");
			else if (e.getMessage().indexOf("UQ_STU_COUR_NUMBER") >= 0)
				throw new RuntimeException("ͬһ�ſ�ֻ��ѡһ�Σ������޸ģ�");
			else
				throw new RuntimeException("�޸ĳ�������ԭ��" + e.getMessage());
		}
	}



	public boolean remove(String studyCourseNumber){
		try {
			return studyCourseDao.delete(studyCourseNumber) > 0 ;
		} catch (SQLException e) {
			if( e.getMessage().indexOf("PK_COURSES") >= 0){
				throw new RuntimeException("ѡ�α�Ų����ڣ�");
			} else {
				throw new RuntimeException("ɾ����������ԭ��" + e.getMessage());
			}
		}
	}

	public boolean modify(StudyCourseBean course, String studyCourseNumber) {
		try {
			//��������
			course.setStudyCourseNumber (course.getStudyCourseNumber().trim());
			course.setStudentNumber(course.getStudentNumber().trim());
			course.setCourseNumber(course.getCourseNumber().trim());

			return studyCourseDao.update(course, studyCourseNumber) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_STUDYCOURSES") >= 0)
				throw new RuntimeException("ѡ�α���Ѵ��ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FQ_COURSES_STUDYCOURSES") >= 0)
				throw new RuntimeException("ѡ�ογ̲����ڣ������޸ģ�");
			else if (e.getMessage().indexOf("FQ_STUDENTS_STUDYCOURSES") >= 0)
				throw new RuntimeException("ѡ��ѧ�������ڣ������޸ģ�");
			else if (e.getMessage().indexOf("UQ_STU_COUR_NUMBER") >= 0)
				throw new RuntimeException("ͬһ�ſ�ֻ��ѡһ�Σ������޸ģ�");
			else
				throw new RuntimeException("�޸ĳ�������ԭ��" + e.getMessage());
		}

	}

	public StudyCourseBean search(String studyCourseNumber){
		try {
			return studyCourseDao.selectOne(studyCourseNumber) ;
		} catch (SQLException e) {
			throw new RuntimeException("���ҳ�������ԭ��" + e.getMessage());
		}
	}

	public List<StudyCourseBean> display(){
		try {
			return studyCourseDao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("��ʾ��������ԭ��" + e.getMessage());
		}
	}

}
