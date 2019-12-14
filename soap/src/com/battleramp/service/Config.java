package com.battleramp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.battleramp.dao.BaseDAO;

public class Config {
	static Logger log = Logger.getLogger(Config.class);

	public static String getProperty(String param) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside getProperty() **** ");
		}

		Properties prop = new Properties();
		InputStream input = null;
		String propertyValue = null;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			try(InputStream resourceStream = loader.getResourceAsStream("config.properties")) {
			    prop.load(resourceStream);
			}
			propertyValue = prop.getProperty(param);
		} catch (IOException ex) {
			log.error("Error inside getProperty() :", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("Error inside getProperty() :", e);
				}
			}
		}
		return propertyValue;

	}
}
