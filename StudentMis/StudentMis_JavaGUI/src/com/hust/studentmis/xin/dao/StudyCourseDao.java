package com.hust.studentmis.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.studentmis.xin.common.MyConnection;
import com.hust.studentmis.xin.entity.CourseBean;
import com.hust.studentmis.xin.entity.StudentBean;
import com.hust.studentmis.xin.entity.StudyCourseBean;

public class StudyCourseDao {


	//单添加
	public int insert(StudyCourseBean  studyCourse) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into STUDYCOURSES " + " 　values (?,?,?,?,?) " ;

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1, studyCourse.getStudyCourseNumber()   );
			pstmt.setString(2,   studyCourse.getStudentNumber()     );
			pstmt.setString(3,  studyCourse.getCourseNumber()    );
			pstmt.setFloat(4,   studyCourse.getScore()   );
			pstmt.setDate(5,  studyCourse.getExamDate()  );

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//主键删除
	public int delete(String studyCourseNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "delete from STUDYCOURSES " + " 　where studyCourseNumber = ?" ;
		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, studyCourseNumber   );
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	// 批量删除，按主键列表删除
	public int delete( String[] studyCourseNumbers ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		String strStudyCourseNumbers = "' '";
		for (int i = 0; studyCourseNumbers != null && i < studyCourseNumbers.length; i++){
			strStudyCourseNumbers += ", '" + studyCourseNumbers + "'";
		}
		String strSql = " DELETE FROM STUDYCOURSES " +
		" WHERE studyCourseNumber IN ( " + strStudyCourseNumbers + " ) ";

		try {
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw e;		} finally {
				MyConnection.closeStatementAndConnection(ps, conn);
			}
	}


	//按主键修改，可修改主键
	public int update(StudyCourseBean  studyCourse,String studyCourseNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		//拼接字符串
		String strSql = " UPDATE STUDYCOURSES " +
		" SET studyCourseNumber = ?,studentNumber= ?, courseNumber = ?, score = ?, examDate = ? " +
		" WHERE studyCourseNumber = ? ";

		try {
			conn =   MyConnection.getConnection();
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1, studyCourse.getStudyCourseNumber()   );
			pstmt.setString(2,   studyCourse.getStudentNumber()     );
			pstmt.setString(3,  studyCourse.getCourseNumber()    );
			pstmt.setFloat(4,   studyCourse.getScore()   );
			pstmt.setDate(5,  studyCourse.getExamDate()  );
			pstmt.setString(6, studyCourseNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//按主键查询一条
	public StudyCourseBean  selectOne(String studyCourseNumber) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
 
		String strSql = " SELECT STUDYCOURSES.*, Students.*, Courses.* " +
						" FROM STUDYCOURSES " +
						" 	INNER JOIN Students ON STUDYCOURSES.StudentNumber = Students.StudentNumber" +
						" 	INNER JOIN Courses ON STUDYCOURSES.CourseNumber = Courses.CourseNumber " +
						" WHERE StudyCourseNumber = ? ";
		
		StudyCourseBean  studyCourse = null;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			pstmt.setString(1, studyCourseNumber);
			rs = pstmt.executeQuery();
			while(rs.next()){
				studyCourse = new StudyCourseBean();
				studyCourse.setStudyCourseNumber(rs.getString(1));
				studyCourse.setStudentNumber(rs.getString(2));
				studyCourse.setCourseNumber(  rs.getString(3));
				studyCourse.setScore (rs.getFloat(4));
				studyCourse.setExamDate(rs.getDate(5));
				
				studyCourse.setStudent(new StudentBean());
				studyCourse.setCourse(new CourseBean());
				studyCourse.getStudent().setStudentNumber(rs.getString(6));
				studyCourse.getStudent().setStudentName(rs.getString(7));
				studyCourse.getStudent().setGender(  rs.getString(8));
				studyCourse.getStudent().setBirthday (rs.getDate(9));
				studyCourse.getStudent().setPhoneNumber (rs.getString(10));
				studyCourse.getStudent().setAddress (rs.getString(11));
				studyCourse.getStudent().setClazzNumber(rs.getString(12));
				studyCourse.getCourse().setCourseNumber(rs.getString(13));
				studyCourse.getCourse().setCourseName(rs.getString(14));
				studyCourse.getCourse().setCredit(rs.getFloat(15));
				studyCourse.getCourse().setHours(rs.getFloat(16));
				studyCourse.getCourse().setDescription(rs.getString(17));
			}
			return studyCourse;
		} catch(SQLException e) {
			throw e;		
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

	//查询全部
	public List<StudyCourseBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql =  "SELECT STUDYCOURSES.*, Students.*, Courses.* " + 
        "  FROM STUDYCOURSES  " + 
        "  INNER JOIN Students ON STUDYCOURSES.StudentNumber = Students.StudentNumber "+ 
        "  INNER JOIN Courses ON STUDYCOURSES.CourseNumber = Courses.CourseNumber ";
		
		
		List<StudyCourseBean > studyCourses = new ArrayList<StudyCourseBean>();;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				StudyCourseBean  studyCourse = new StudyCourseBean();
				studyCourse.setStudyCourseNumber(rs.getString(1));
				studyCourse.setStudentNumber(rs.getString(2));
				studyCourse.setCourseNumber( rs.getString(3));
				studyCourse.setScore (rs.getFloat(4));
				studyCourse.setExamDate(rs.getDate(5));
				
				studyCourse.setStudent(new StudentBean());
				studyCourse.setCourse(new CourseBean());
				studyCourse.getStudent().setStudentNumber(rs.getString(6));
				studyCourse.getStudent().setStudentName(rs.getString(7));
				studyCourse.getStudent().setGender(  rs.getString(8));
				studyCourse.getStudent().setBirthday (rs.getDate(9));
				studyCourse.getStudent().setPhoneNumber (rs.getString(10));
				studyCourse.getStudent().setAddress (rs.getString(11));
				studyCourse.getStudent().setClazzNumber(rs.getString(12));
				studyCourse.getStudent().setEmail(rs.getString(13));
				studyCourse.getCourse().setCourseNumber(rs.getString(14));
				studyCourse.getCourse().setCourseName(rs.getString(15));
				studyCourse.getCourse().setCredit(rs.getFloat(16));
				studyCourse.getCourse().setHours(rs.getFloat(17));
				studyCourse.getCourse().setDescription(rs.getString(18));
				
				studyCourses.add(studyCourse);
			}
			return studyCourses;
		} catch(SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


}
