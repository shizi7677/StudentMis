package com.neuedu.guomy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.guomy.common.MyConnection;
import com.neuedu.guomy.entity.LoginBean;

public class LoginDao {
	MyConnection myConn = new MyConnection();
	
	/**
	 * ���һ����¼
	 * @param Login
	 * @return
	 * @throws SQLException 
	 */
	public int insert( LoginBean login ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String strSql = " INSERT INTO Logins " +
							" VALUES ( ?, ? ) ";
			conn = myConn.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(1, login.getLoginName());
			ps.setString(2, login.getPassword());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeStatementAndConnection(ps, conn);
		}
	}
	
	/**
	 * ʹ��JDBC�����̽���������ӣ�����ӻ򶼲����
	 * @param Logins
	 * @return
	 */
	public int insert(List<LoginBean> logins) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			conn = myConn.getConnection();
			conn.setAutoCommit(false);
			
			for (LoginBean login: logins) {
				String strSql = " INSERT INTO Logins " +
								" VALUES ( ?, ? ) ";

				ps = conn.prepareStatement(strSql);
				ps.setString(1, login.getLoginName());
				ps.setString(2, login.getPassword());
					
				count += ps.executeUpdate();
			}
			conn.commit();
			return count;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeStatementAndConnection(ps, conn);
		}
	}
	
	/**
	 * δʹ��JDBC�����̽���������ӣ��������
	 * @param Logins
	 * @return
	 */
	public int insert2(List<LoginBean> logins) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			for (LoginBean login: logins) {
				String strSql = " INSERT INTO Logins " +
								" VALUES ( ?, ? ) ";
				conn = myConn.getConnection();

				try {
					ps = conn.prepareStatement(strSql);
					ps.setString(1, login.getLoginName());
					ps.setString(2, login.getPassword());
					
					count += ps.executeUpdate();
				} catch (SQLException ex) { }
			}
			return count;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeStatementAndConnection(ps, conn);
		}
	}
	
	/**
	 * ������ɾ��
	 */
	public int delete( String loginName ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String strSql = " DELETE FROM Logins " +
							" WHERE LoginName = ? ";
			conn = myConn.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(1, loginName);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeStatementAndConnection(ps, conn);
		}
	}

	/**
	 * ����ɾ�����������б�ɾ��
	 * @param LoginNumbers
	 * @return
	 */
	public int delete( String [] loginNames ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String strLoginNames = "' '";
			for (int i = 0; loginNames != null && i < loginNames.length; i++)
				strLoginNames += ", '" + loginNames + "'";
			
			String strSql = " DELETE FROM Logins " +
							" WHERE LoginName IN ( " + strLoginNames + " ) ";
			conn = myConn.getConnection();
			ps = conn.prepareStatement(strSql);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeStatementAndConnection(ps, conn);
		}
	}

	/**
	 * �޸ģ����޸�����
	 * @param Login �޸ĺ�Ķ�����Ϣ
	 * @param LoginNumber ԭ����ֵ
	 * @return
	 */
	public int update( LoginBean login, String loginName ) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String strSql = " UPDATE Logins " +
							" SET LoginName = ?, Password = ? " +
							" WHERE LoginName = ? ";
			conn = myConn.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(1, login.getLoginName());
			ps.setString(2, login.getPassword());
			ps.setString(3, loginName);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeStatementAndConnection(ps, conn);
		}
	}
	
	/**
	 * ������ȡһ������
	 * @return
	 */
	public LoginBean select(String loginName) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String strSql = " SELECT * " +
							" FROM Logins " +
							" WHERE LoginName = ? ";
			LoginBean login = null;
			conn = myConn.getConnection();
			ps = conn.prepareStatement(strSql);
			ps.setString(1, loginName);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				login = new LoginBean();
				login.setLoginName(rs.getString(1));
				login.setPassword(rs.getString(2));
			}
			return login;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
	
	/**
	 * ��ȡ��������
	 * @return
	 * @throws SQLException 
	 */
	public List<LoginBean> selectAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LoginBean> logins = new ArrayList<LoginBean>();
		
		try {
			String strSql = " SELECT * " +
							" FROM Logins ";
			conn = myConn.getConnection();
			ps = conn.prepareStatement(strSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				LoginBean 
				login = new LoginBean();
				login.setLoginName(rs.getString(1));
				login.setPassword(rs.getString(2));
				
				logins.add(login);	// 
			}
			return logins;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw e;
		} finally {
			myConn.closeResultSetAndStatementAndConnection(rs, ps, conn);
		}
	}
	
}







