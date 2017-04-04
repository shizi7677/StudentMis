package com.neuedu.guomy.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	
	// ��̬�飬�ڼ�����ʱ����һ�Σ��൱����Ĺ��췽������
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Oracle������δ�������Oracle������������");
		}
	}
	
	public  Connection getConnection() throws SQLException {
		try {
			return DriverManager.getConnection(URL, "scott", "tiger");
		} catch (SQLException e) {
			// No suitable driver found for jdbc:oracle:thin:@127.0.0.1:1521:orcl ��������ʱ������δ���ص�JVM
			if (e.getMessage().indexOf("No suitable driver found for") >= 0)
				System.out.println("��������ʱ������δ���ص�JVM");
			// Io �쳣: The Network Adapter could not establish the connection ������δ���������δ����
			else if (e.getMessage().indexOf("Io �쳣: The Network Adapter could not establish the connection") >= 0)
				System.out.println("������δ���������δ����");
			// Listener refused the connection with the following error: ORA-12505... ���ݿ����ƴ��������ԭ��
			else if (e.getMessage().indexOf("Listener refused the connection with the following error:") >= 0)
				System.out.println("���ݿ����ƴ��������ԭ��");
			// ORA-01017: invalid username/password; logon denied �û������������
			else if (e.getMessage().indexOf("ORA-01017") >= 0)
				System.out.println("�û������������");
			else
				e.printStackTrace();
			
			throw e;
		}
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeStatement(Statement st) {
		try {
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeStatementAndConnection(Statement st, Connection conn) {
		closeStatement(st);
		closeConnection(conn);
	}

	public void closeResultSetAndStatementAndConnection(ResultSet rs,
			Statement st, Connection conn) {
		closeResultSet(rs);
		closeStatement(st);
		closeConnection(conn);
		
	}

}
