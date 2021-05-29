package org.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
<<<<<<< HEAD
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
=======

@EnableEurekaClient
>>>>>>> c34771a9c456b15f20c99bdc87b0a106c75d3180
@SpringBootApplication
public class ApiGatewaySpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewaySpringBootApplication.class, args);
	}
}
