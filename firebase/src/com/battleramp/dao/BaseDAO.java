package com.battleramp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.battleramp.service.Config;

@Component
public class BaseDAO {
	Connection connection;
	static Logger log = Logger.getLogger(BaseDAO.class);

	public BaseDAO() {
		this.connection = null;
	}

	public Connection openConnection() {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside openConnection() **** ");
		}
		try {
			Class.forName(Config.getProperty("database.driver"));
			this.connection = DriverManager.getConnection(
					Config.getProperty("database.url") + Config.getProperty("database.schema"),
					Config.getProperty("database.username"), Config.getProperty("database.password"));

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
