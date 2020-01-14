package org.api.controller;

import org.api.configuration.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private Environment env;

	@Autowired
	private ApiConfig apiConfig;

	@RequestMapping()
	public String test() {
		return apiConfig.getDefaultMessage() + env.getProperty("app.messages");
	}

}
