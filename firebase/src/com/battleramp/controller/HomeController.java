package com.battleramp.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("*")
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String testMethod() {
		return "home";
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String facebookPage() throws IOException, URISyntaxException {
		return "test";
	}

}