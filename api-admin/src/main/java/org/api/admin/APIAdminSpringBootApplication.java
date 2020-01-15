package org.api.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAdminServer
@SpringBootApplication
public class APIAdminSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIAdminSpringBootApplication.class, args);
	}
	
}
