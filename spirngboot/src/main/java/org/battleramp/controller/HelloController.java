package org.battleramp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
     
	@RequestMapping("/hello")
	public String sayHi() {
		return "HI";
	}
	
	
	@RequestMapping("test")
	public String sayHwi() {
		return "HI";
	}
}
