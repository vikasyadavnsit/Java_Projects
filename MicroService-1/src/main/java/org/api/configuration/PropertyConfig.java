package org.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@PropertySource("classpath:config-${spring.profiles.active}.properties")
@ConfigurationProperties("api") // prefix app, find api.* values
@Getter
@Setter
public class PropertyConfig {

	private String defaultMessage;
	private String authenticationCallBackURL;

}
