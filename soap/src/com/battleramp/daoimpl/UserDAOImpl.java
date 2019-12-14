package com.battleramp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.battleramp.dao.BaseDAO;
import com.battleramp.dao.UserDAO;
import com.sun.jna.platform.win32.SetupApi;

@Component
public class UserDAOImpl implements UserDAO {
	static Logger log = Logger.getLogger(UserDAOImpl.class);
    
	@Autowired
	private BaseDAO baseDAO ;

	public void setUserData(String userName, String email, String OAuthProvider, JSONObject data) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside setUserData() **** ");
		}
		Connection connection = baseDAO.openConnection();
		PreparedStatement statement = null;

		String sql = "INSERT INTO LOGIN_CREDENTIALS (USER_NAME, EMAIL_ID, OAUTH_PROVIDER , JSON_DATA) VALUES (?, ?, ?, ?)";

		try {
			int parameterIndex = 1;
			statement = connection.prepareStatement(sql);
			statement.setString(parameterIndex++, userName);
			statement.setString(parameterIndex++, email);
			statement.setString(parameterIndex++, OAuthProvider);
			statement.setString(parameterIndex++, data.toString());

			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("Error inside setUserData() :", e);
		} finally {
			baseDAO.closeConnection();
		}
		

	}

}
