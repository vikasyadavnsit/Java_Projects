package org.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Microservice1SpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(Microservice1SpringBootApplication.class, args);
	}
}
