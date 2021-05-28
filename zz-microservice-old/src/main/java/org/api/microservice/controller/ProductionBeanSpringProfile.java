package org.api.microservice.controller;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProductionBeanSpringProfile {

	@PostConstruct
	private void constructed() {
		System.out.println("ProductionBeanSpringProfile Bean Created.");
	}
}
