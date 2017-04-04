package com.hust.studentmis.xin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.studentmis.xin.common.MyConnection;
import com.hust.studentmis.xin.entity.StudentBean;

public class StudentDao {


	//单添加
	public int insert(StudentBean student) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "insert into studentS " + " 　values (?,?,?,?,?,?,?) " ;

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  student.getStudentNumber()   );
			pstmt.setString(2, student.getStudentName()  );
			pstmt.setString(3,   student.getGender()    );
			pstmt.setDate(4,   student.getBirthday()  );
			pstmt.setString(5,  student.getPhoneNumber() );
			pstmt.setString(6,  student.getAddress()  );
			pstmt.setString(7,  student.getClazzNumber() );

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//主键删除
	public int delete(String studentNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String strSql = "delete from studentS " + " 　where studentNumber = ?" ;
		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, studentNumber   );
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	// 批量删除，按主键列表删除
	public int delete( String[] studentNumbers ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;

		String strstudentNumbers = "' '";
		for (int i = 0; studentNumbers != null && i < studentNumbers.length; i++){
			strstudentNumbers += ", '" + studentNumbers + "'";
		}
		String strSql = " DELETE FROM students " +
		" WHERE studentNumber IN ( " + strstudentNumbers + " ) ";

		try {
			conn = MyConnection.getConnection();
			ps = conn.prepareStatement(strSql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw e;		} finally {
				MyConnection.closeStatementAndConnection(ps, conn);
			}
	}


	//按主键修改
	public int update(StudentBean student,String studentNumber) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		//拼接字符串
		String strSql = " UPDATE students " +
		" SET studentNumber = ?, studentName = ?, Gender = ?, Birthday = ?, PhoneNumber = ?,Address = ?, ClazzNumber = ? " +
		" WHERE studentNumber = ? ";

		try {
			conn =   MyConnection.getConnection();;
			pstmt = conn.prepareStatement(strSql);

			pstmt.setString(1,  student.getStudentNumber() );
			pstmt.setString(2, student.getStudentName()  );
			pstmt.setString(3,   student.getGender()    );
			pstmt.setDate(4,   student.getBirthday()   );
			pstmt.setString(5,  student.getPhoneNumber() );
			pstmt.setString(6,  student.getAddress() );
			pstmt.setString(7,  student.getClazzNumber() );
			pstmt.setString(8, studentNumber);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


	//按主键查询一条
	public StudentBean selectOne(String studentNumber) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *　from students where studentNumber=?";
		StudentBean student = null;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			pstmt.setString(1, studentNumber);
			rs = pstmt.executeQuery();
			while(rs.next()){
				student = new StudentBean();
				student.setStudentNumber(rs.getString(1));
				student.setStudentName(rs.getString(2));
				student.setGender(  rs.getString(3));
				student.setBirthday (rs.getDate(4));
				student.setPhoneNumber (rs.getString(5));
				student.setAddress (rs.getString(6));
				student.setClazzNumber( rs.getString(7));
			}
			return student;
		} catch(SQLException e) {
			throw e;		
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}

	//查询全部
	public List<StudentBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strSql = "select *　from Students";
		List<StudentBean> students =  new ArrayList<StudentBean>();;

		try{
			conn = MyConnection.getConnection();
			pstmt = conn.prepareCall(strSql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				StudentBean student = new StudentBean();
				student.setStudentNumber(rs.getString(1));
				student.setStudentName(rs.getString(2));
				student.setGender(  rs.getString(3));
				student.setBirthday (rs.getDate(4));
				student.setPhoneNumber (rs.getString(5));
				student.setAddress (rs.getString(6));
				student.setClazzNumber( rs.getString(7));
				students.add(student);
			}
			return students;
		} catch(SQLException e) {
			throw e;
		} finally {
			MyConnection.closeStatementAndConnection(pstmt, conn);
		}
	}


}
