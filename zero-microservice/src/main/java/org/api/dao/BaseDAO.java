package org.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.api.controller.authentication.oauth.OauthAuthenticationConfig;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BaseDAO {
	Connection connection;

	public BaseDAO() {
		this.connection = null;
	}

	public Connection openConnection() {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside openConnection() **** ");
		}
		try {
			Class.forName(OauthAuthenticationConfig.getProperty("database.driver"));
			this.connection = DriverManager.getConnection(
					OauthAuthenticationConfig.getProperty("database.url") + OauthAuthenticationConfig.getProperty("database.schema"),
					OauthAuthenticationConfig.getProperty("database.username"), OauthAuthenticationConfig.getProperty("database.password"));

		} catch (ClassNotFoundException e) {
			log.error("Error in openConnection() :", e);
		} catch (SQLException e) {
			log.error("Error in openConnection() :", e);
		}
		return this.connection;
	}

	public void closeConnection() {

		if (log.isDebugEnabled()) {
			log.debug("**** Inside closeConnection() **** ");
		}

		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				log.error("Error in closeConnection :", e);
			}
		}
	}

}
