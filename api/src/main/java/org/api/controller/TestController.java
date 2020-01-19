package org.api.controller;

import org.api.configuration.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Environment env;

	@Autowired
	private ApiConfig apiConfig;

	@GetMapping("/")
	public String all() {
		return "Everyone";
	}

	@GetMapping("/user")
	public String allUser() {
		return "ALL USERS";
	}

	@GetMapping("/admin")
	public String allAdmin() {
		return "ALL ADMINS";
	}

	@RequestMapping("/test")
	public String test() throws InterruptedException {
		Thread.sleep(5000);
		return apiConfig.getDefaultMessage() + env.getProperty("app.messages");
	}

}
