package org.api.microservice.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Configuration
@ConfigurationProperties("db")
public class DBSettingConfigurationProperties {
	private String connection;
	private String host;
	private int port;
}
