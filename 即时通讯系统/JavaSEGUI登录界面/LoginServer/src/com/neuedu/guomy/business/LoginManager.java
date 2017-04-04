package com.neuedu.guomy.business;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.guomy.dao.LoginDao;
import com.neuedu.guomy.entity.LoginBean;

public class LoginManager {
	private LoginDao dao = new LoginDao();
	
	public boolean add(LoginBean login) {
		try {
			// ��������
			login.setLoginName(login.getLoginName().trim());
			
			// ������֤
			
			return dao.insert(login) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_LOGINS") >= 0)
				throw new RuntimeException("��¼���Ѵ��ڣ�������ӣ�");
			else
				throw new RuntimeException("���ʱ����ԭ��" + e.getMessage());
		}
	}

	public boolean remove(String loginName) {
		try {
			return dao.delete(loginName) > 0;
		} catch (SQLException e) {
			throw new RuntimeException("ɾ��ʱ����ԭ��" + e.getMessage());
		}
	}

	public boolean modify(LoginBean login, String loginName) {
		try {
			// ��������
			login.setLoginName(login.getLoginName().trim());
			
			// ������֤
			
			return dao.update(login, loginName) > 0;
		} catch (SQLException e) {
			if (e.getMessage().indexOf("PK_LOGINS") >= 0)
				throw new RuntimeException("��¼���Ѵ��ڣ������޸ģ�");
			else
				throw new RuntimeException("�޸�ʱ����ԭ��" + e.getMessage());
		}
	}

	public LoginBean search(String loginName) {
		try {
			return dao.select(loginName);
		} catch (SQLException e) {
			throw new RuntimeException("����ʱ����ԭ��" + e.getMessage());
		}
	}

	public List<LoginBean> search() {
		try {
			return dao.selectAll();
		} catch (SQLException e) {
			throw new RuntimeException("����ʱ����ԭ��" + e.getMessage());
		}
	}
	
	public LoginBean isLogin(String loginName, String password) {
		LoginBean login = null;
		login = search(loginName);
		if (login != null && login.getPassword().compareTo(password) == 0) {
			return login;
		}
		return null;
	}

}
