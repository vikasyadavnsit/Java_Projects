package com.battleramp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class WildcardController {

	@RequestMapping("*")
	public String get() {
		return "This page doesn't exist.";
	}
}
