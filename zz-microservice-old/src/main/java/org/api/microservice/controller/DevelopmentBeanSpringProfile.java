package org.api.microservice.controller;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevelopmentBeanSpringProfile {

	@PostConstruct
	private void constructed() {
		System.out.println("DevelopmentBeanSpringProfile Bean Created.");
	}

}
