package org.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDAOImpl implements UserDAO {

	@Autowired
	private BaseDAO baseDAO;

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
		} catch (Exception e) {
			log.error("Error inside setUserData() :", e);
		} finally {
			baseDAO.closeConnection();
		}

	}

}
