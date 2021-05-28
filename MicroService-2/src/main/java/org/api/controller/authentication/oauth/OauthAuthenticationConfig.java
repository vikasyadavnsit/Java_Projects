package org.api.controller.authentication.oauth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OauthAuthenticationConfig {

	public static String getProperty(String param) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside getProperty() **** ");
		}

		Properties prop = new Properties();
		InputStream input = null;
		String propertyValue = null;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			try (InputStream resourceStream = loader.getResourceAsStream("config.properties")) {
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
