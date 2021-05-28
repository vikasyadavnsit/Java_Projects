package org.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerSpringBootApplication.class, args);
	}

}
